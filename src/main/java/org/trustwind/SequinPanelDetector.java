package org.trustwind;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Real-time detector for brightness changes in reflective elements (sequins)
 * on a panel using video stream processing with JSON output.
 */
public class SequinPanelDetector {

  // Load OpenCV native library
  static {
    nu.pattern.OpenCV.loadLocally();
  }

  private final int videoSource;
  private final int fps;
  private final double frameInterval;
  private final double contrastIncrease;
  private final double brightnessThreshold;
  private final String outputDir;
  private final String[] gpsCoordinates;

  private VideoCapture cap;
  private Mat currentFrame;
  private Mat previousFrame;

  private List<MatOfPoint> segments;
  private Map<Integer, Integer> segmentStates;

  private volatile boolean isRunning;
  private Thread processingThread;

  // JSON serialization
  private final Gson gson;

  // GUI components
  private JFrame displayFrame;
  private JLabel imageLabel;
  private JLabel statusLabel;

  /**
   * Data structure for JSON output.
   */
  public static class FrameData {
    private String timeDate;
    private String[] gps;
    private int[] values;

    public FrameData(String timeDate, String[] gps, int[] values) {
      this.timeDate = timeDate;
      this.gps = gps;
      this.values = values;
    }

    // Getters for Gson serialization
    public String getTimeDate() { return timeDate; }
    public String[] getGps() { return gps; }
    public int[] getValues() { return values; }
  }

  /**
   * Initialize the sequin panel detector.
   *
   * @param videoSource Camera index or video source
   * @param fps Target frames per second for processing
   * @param contrastIncrease Contrast increase factor (0.2 = 20%)
   * @param brightnessThreshold Brightness change threshold (0.2 = 20%)
   * @param outputDir Directory to save JSON files
   * @param gpsCoordinates GPS coordinates [latitude, longitude]
   */
  public SequinPanelDetector(int videoSource, int fps, double contrastIncrease,
      double brightnessThreshold, String outputDir, String[] gpsCoordinates) {
    this.videoSource = videoSource;
    this.fps = fps;
    this.frameInterval = 1000.0 / fps; // Convert to milliseconds
    this.contrastIncrease = contrastIncrease;
    this.brightnessThreshold = brightnessThreshold;
    this.outputDir = outputDir;
    this.gpsCoordinates = gpsCoordinates != null ? gpsCoordinates : new String[]{"51.0258", "52.0236"};

    this.cap = new VideoCapture();
    this.segments = new ArrayList<>();
    this.segmentStates = new ConcurrentHashMap<>();
    this.isRunning = false;

    // Initialize JSON serialization with custom formatting
    this.gson = new GsonBuilder()
        .setPrettyPrinting()
        .setFieldNamingStrategy(field -> {
          // Convert camelCase to kebab-case for JSON field names
          String name = field.getName();
          if ("timeDate".equals(name)) return "time-date";
          return name;
        })
        .create();

    // Create output directory
    createOutputDirectory();
    initializeGUI();
  }

  /**
   * Create output directory if it doesn't exist.
   */
  private void createOutputDirectory() {
    try {
      Path outputPath = Paths.get(outputDir);
      if (!Files.exists(outputPath)) {
        Files.createDirectories(outputPath);
        System.out.println("Created output directory: " + outputPath.toAbsolutePath());
      }
    } catch (IOException e) {
      System.err.println("Error creating output directory: " + e.getMessage());
    }
  }

  /**
   * Initialize GUI components for displaying video.
   */
  private void initializeGUI() {
    displayFrame = new JFrame("Sequin Panel Detector");
    displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    displayFrame.setLayout(new BorderLayout());

    imageLabel = new JLabel();
    imageLabel.setHorizontalAlignment(JLabel.CENTER);
    displayFrame.add(imageLabel, BorderLayout.CENTER);

    // Add status panel
    JPanel statusPanel = new JPanel(new BorderLayout());
    statusLabel = new JLabel("Initializing...");
    statusLabel.setHorizontalAlignment(JLabel.CENTER);
    statusPanel.add(statusLabel, BorderLayout.CENTER);

    // Add control panel
    JPanel controlPanel = new JPanel();
    JButton stopButton = new JButton("Stop Detection");
    stopButton.addActionListener(e -> stopDetection());
    controlPanel.add(stopButton);

    statusPanel.add(controlPanel, BorderLayout.SOUTH);
    displayFrame.add(statusPanel, BorderLayout.SOUTH);

    displayFrame.setSize(800, 700);
    displayFrame.setLocationRelativeTo(null);
  }

  /**
   * Initialize camera connection.
   *
   * @return true if camera initialized successfully, false otherwise
   */
  public boolean initializeCamera() {
    try {
      cap.open(videoSource);
      if (!cap.isOpened()) {
        System.err.println("Error: Could not open camera");
        return false;
      }

      // Set camera properties for optimal performance
      cap.set(Videoio.CAP_PROP_FPS, fps);
      cap.set(Videoio.CAP_PROP_FRAME_WIDTH, 640);
      cap.set(Videoio.CAP_PROP_FRAME_HEIGHT, 480);

      System.out.println("Camera initialized successfully");
      return true;

    } catch (Exception e) {
      System.err.println("Error initializing camera: " + e.getMessage());
      return false;
    }
  }

  /**
   * Create triangular segments (Ent) across the frame.
   * Each segment represents a reflective element.
   *
   * @param frameWidth Width of the video frame
   * @param frameHeight Height of the video frame
   * @param triangleSize Size of each triangular segment
   * @return List of triangular segments as MatOfPoint objects
   */
  private List<MatOfPoint> createTriangularSegments(int frameWidth, int frameHeight, int triangleSize) {
    List<MatOfPoint> segments = new ArrayList<>();

    // Calculate number of triangles that fit in the frame
    int cols = frameWidth / triangleSize;
    int rows = (int) (frameHeight / (triangleSize * Math.sqrt(3) / 2));

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        // Calculate triangle vertices
        double xOffset = col * triangleSize;
        double yOffset = row * triangleSize * Math.sqrt(3) / 2;

        Point[] vertices = new Point[3];

        // Alternate triangle orientation for tessellation
        if ((row + col) % 2 == 0) {
          // Upward pointing triangle
          vertices[0] = new Point(xOffset, yOffset + triangleSize * Math.sqrt(3) / 2);
          vertices[1] = new Point(xOffset + triangleSize, yOffset + triangleSize * Math.sqrt(3) / 2);
          vertices[2] = new Point(xOffset + triangleSize / 2, yOffset);
        } else {
          // Downward pointing triangle
          vertices[0] = new Point(xOffset, yOffset);
          vertices[1] = new Point(xOffset + triangleSize, yOffset);
          vertices[2] = new Point(xOffset + triangleSize / 2, yOffset + triangleSize * Math.sqrt(3) / 2);
        }

        MatOfPoint triangle = new MatOfPoint(vertices);
        segments.add(triangle);
      }
    }

    return segments;
  }

  /**
   * Increase frame contrast by specified percentage.
   *
   * @param frame Input frame
   * @return Frame with increased contrast
   */
  private Mat increaseContrast(Mat frame) {
    Mat enhanced = new Mat();

    // Convert to float for precise calculations
    Mat frameFloat = new Mat();
    frame.convertTo(frameFloat, CvType.CV_32F);

    // Increase contrast: new_pixel = (pixel - 128) * (1 + contrast_factor) + 128
    double contrastFactor = 1 + contrastIncrease;
    Core.subtract(frameFloat, new Scalar(128, 128, 128), frameFloat);
    Core.multiply(frameFloat, new Scalar(contrastFactor, contrastFactor, contrastFactor), frameFloat);
    Core.add(frameFloat, new Scalar(128, 128, 128), frameFloat);

    // Convert back to 8-bit and clip values
    frameFloat.convertTo(enhanced, CvType.CV_8U);

    return enhanced;
  }

  /**
   * Calculate average brightness of a triangular segment.
   *
   * @param frame Input frame (grayscale)
   * @param triangle Triangle vertices as MatOfPoint
   * @return Average brightness value (0.0 to 1.0)
   */
  private double calculateSegmentBrightness(Mat frame, MatOfPoint triangle) {
    // Create mask for the triangular segment
    Mat mask = Mat.zeros(frame.size(), CvType.CV_8U);
    List<MatOfPoint> triangles = Arrays.asList(triangle);
    Imgproc.fillPoly(mask, triangles, new Scalar(255));

    // Calculate mean brightness within the triangle
    Scalar meanBrightness = Core.mean(frame, mask);

    // Normalize to 0-1 range
    return meanBrightness.val[0] / 255.0;
  }

  /**
   * Process a single frame to detect brightness changes in segments.
   *
   * @param frame Input frame
   * @return Map of segment index to state (0 or 1)
   */
  private Map<Integer, Integer> processFrame(Mat frame) {
    // Increase contrast by specified percentage
    Mat enhancedFrame = increaseContrast(frame);

    // Convert to grayscale for brightness calculation
    Mat grayFrame = new Mat();
    Imgproc.cvtColor(enhancedFrame, grayFrame, Imgproc.COLOR_BGR2GRAY);

    Map<Integer, Integer> segmentStates = new HashMap<>();

    // Process each segment
    for (int i = 0; i < segments.size(); i++) {
      MatOfPoint triangle = segments.get(i);

      // Calculate current brightness
      double currentBrightness = calculateSegmentBrightness(grayFrame, triangle);

      // Compare with previous frame if available
      if (previousFrame != null) {
        Mat prevGray = new Mat();
        Imgproc.cvtColor(previousFrame, prevGray, Imgproc.COLOR_BGR2GRAY);
        double prevBrightness = calculateSegmentBrightness(prevGray, triangle);

        // Determine segment state based on brightness change
        double brightnessIncrease = currentBrightness - prevBrightness;

        if (brightnessIncrease > brightnessThreshold) {
          segmentStates.put(i, 1); // Brightness increased significantly
        } else {
          segmentStates.put(i, 0); // No significant brightness increase
        }
      } else {
        // First frame - set all segments to 0
        segmentStates.put(i, 0);
      }
    }

    return segmentStates;
  }

  /**
   * Generate timestamp and filename for JSON output.
   *
   * @return Array containing [timestamp, filename]
   */
  private String[] generateTimestampFilename() {
    ZonedDateTime now = ZonedDateTime.now();

    // Format timestamp for JSON (ISO format with timezone)
    String timestampIso = now.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    // Format filename: YYYY-MM-DD_HH-mm-ss.nnnnnnnnn.json
    String dateStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String timeStr = now.format(DateTimeFormatter.ofPattern("HH-mm-ss.nnnnnnnnn"));
    String filename = String.format("%s_%s.json", dateStr, timeStr);

    return new String[]{timestampIso, filename};
  }

  /**
   * Save frame processing results to JSON file.
   *
   * @param segmentStates Map of segment states
   */
  private void saveFrameData(Map<Integer, Integer> segmentStates) {
    try {
      // Generate timestamp and filename
      String[] timestampFilename = generateTimestampFilename();
      String timestamp = timestampFilename[0];
      String filename = timestampFilename[1];

      // Convert segment states to values array (ordered by segment index)
      int[] values = new int[segments.size()];
      for (int i = 0; i < segments.size(); i++) {
        values[i] = segmentStates.getOrDefault(i, 0);
      }

      // Create frame data object
      FrameData frameData = new FrameData(timestamp, gpsCoordinates.clone(), values);

      // Save to file
      Path filepath = Paths.get(outputDir, filename);
      try (FileWriter writer = new FileWriter(filepath.toFile())) {
        gson.toJson(frameData, writer);
      }

      int activeSegments = Arrays.stream(values).sum();
      System.out.println(String.format("Saved frame data: %s (Active segments: %d/%d)",
          filename, activeSegments, values.length));

    } catch (Exception e) {
      System.err.println("Error saving frame data: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Visualize segments on the frame with color coding based on state.
   *
   * @param frame Input frame
   * @param segmentStates Map of segment states
   * @return Frame with overlaid segment visualization
   */
  private Mat visualizeSegments(Mat frame, Map<Integer, Integer> segmentStates) {
    Mat visualization = frame.clone();

    for (int i = 0; i < segments.size(); i++) {
      MatOfPoint triangle = segments.get(i);
      int state = segmentStates.getOrDefault(i, 0);

      // Color coding: Green for state 1 (brightened), Blue for state 0
      Scalar color = state == 1 ? new Scalar(0, 255, 0) : new Scalar(255, 0, 0);

      // Draw triangle outline
      List<MatOfPoint> triangles = Arrays.asList(triangle);
      Imgproc.polylines(visualization, triangles, true, color, 1);

      // Fill triangle with semi-transparent color if brightened
      if (state == 1) {
        Mat overlay = visualization.clone();
        Imgproc.fillPoly(overlay, triangles, color);
        Core.addWeighted(visualization, 0.8, overlay, 0.2, 0, visualization);
      }
    }

    return visualization;
  }

  /**
   * Convert OpenCV Mat to BufferedImage for GUI display.
   *
   * @param mat OpenCV Mat object
   * @return BufferedImage for Swing display
   */
  private BufferedImage matToBufferedImage(Mat mat) {
    int type = BufferedImage.TYPE_BYTE_GRAY;
    if (mat.channels() > 1) {
      type = BufferedImage.TYPE_3BYTE_BGR;
    }

    int bufferSize = mat.channels() * mat.cols() * mat.rows();
    byte[] buffer = new byte[bufferSize];
    mat.get(0, 0, buffer); // get all pixels

    BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
    final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
    System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);

    return image;
  }

  /**
   * Update GPS coordinates from external source.
   *
   * @param latitude Latitude as string
   * @param longitude Longitude as string
   */
  public void updateGpsCoordinates(String latitude, String longitude) {
    this.gpsCoordinates[0] = latitude;
    this.gpsCoordinates[1] = longitude;
  }

  /**
   * Main processing loop for real-time video analysis.
   */
  private void processingLoop() {
    long lastProcessTime = System.currentTimeMillis();
    Mat frame = new Mat();
    int frameCount = 0;

    while (isRunning) {
      boolean ret = cap.read(frame);
      if (!ret) {
        System.err.println("Error: Could not read frame");
        break;
      }

      long currentTime = System.currentTimeMillis();

      // Process frame at target FPS
      if (currentTime - lastProcessTime >= frameInterval) {
        // Initialize segments on first frame
        if (segments.isEmpty()) {
          Size frameSize = frame.size();
          segments = createTriangularSegments((int) frameSize.width, (int) frameSize.height, 30);
          System.out.println("Created " + segments.size() + " triangular segments");
        }

        // Process current frame
        Map<Integer, Integer> states = processFrame(frame);
        segmentStates.putAll(states);

        // Save frame data to JSON file
        saveFrameData(states);

        // Create visualization
        Mat visualization = visualizeSegments(frame, states);

        // Update GUI
        int finalFrameCount = frameCount;
        SwingUtilities.invokeLater(() -> {
          BufferedImage bufferedImage = matToBufferedImage(visualization);
          imageLabel.setIcon(new ImageIcon(bufferedImage));

          // Update status
          int activeSegments = states.values().stream().mapToInt(Integer::intValue).sum();
          String status = String.format("Frame: %d | Active segments: %d/%d | Output: %s",
              finalFrameCount + 1, activeSegments, segments.size(),
              Paths.get(outputDir).toAbsolutePath());
          statusLabel.setText(status);

          displayFrame.repaint();
        });

        // Update frame history
        if (currentFrame != null) {
          if (previousFrame != null) {
            previousFrame.release();
          }
          previousFrame = currentFrame.clone();
        }
        currentFrame = frame.clone();

        lastProcessTime = currentTime;
        frameCount++;

        // Display processing statistics
        if (frameCount % 30 == 0) { // Every 2 seconds at 15fps
          int activeSegments = states.values().stream().mapToInt(Integer::intValue).sum();
          System.out.println(String.format("Processed %d frames. Current active segments: %d/%d",
              frameCount, activeSegments, segments.size()));
        }
      }

      // Small delay to prevent excessive CPU usage
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
      }
    }
  }

  /**
   * Start the real-time detection process.
   */
  public void startDetection() {
    if (!initializeCamera()) {
      return;
    }

    isRunning = true;
    displayFrame.setVisible(true);

    // Start processing in separate thread
    processingThread = new Thread(this::processingLoop);
    processingThread.start();

    System.out.println("Detection started. JSON files will be saved to: " +
        Paths.get(outputDir).toAbsolutePath());
    System.out.println("Click 'Stop Detection' to quit.");
  }

  /**
   * Stop the detection process and cleanup resources.
   */
  public void stopDetection() {
    isRunning = false;

    if (processingThread != null) {
      try {
        processingThread.join(1000); // Wait up to 1 second
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    if (cap != null && cap.isOpened()) {
      cap.release();
    }

    // Release Mat objects
    if (currentFrame != null) currentFrame.release();
    if (previousFrame != null) previousFrame.release();

    displayFrame.dispose();
    System.out.println("Detection stopped.");
    System.exit(0);
  }

  /**
   * Get current segment states.
   *
   * @return Map of segment index to state (0 or 1)
   */
  public Map<Integer, Integer> getCurrentStates() {
    return new HashMap<>(segmentStates);
  }

  /**
   * Main method to run the sequin panel detector.
   */
  public static void main(String[] args) {
    // Create detector instance
    // For RaspberryPI camera stream, modify video source accordingly
    SequinPanelDetector detector = new SequinPanelDetector(
        0,      // Video source (camera index or stream URL)
        15,     // FPS
        0.2,    // 20% contrast increase
        0.2,    // 20% brightness threshold
        "sequin_output", // Output directory
        new String[]{"51.0258", "52.0236"} // GPS coordinates
    );

    // Add shutdown hook for cleanup
    Runtime.getRuntime().addShutdownHook(new Thread(detector::stopDetection));

    try {
      detector.startDetection();
    } catch (Exception e) {
      System.err.println("Error during detection: " + e.getMessage());
      e.printStackTrace();
    }
  }
}


