package org.smalltasks;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.stream.IntStream;

public class GenerateWeekTemplate {

  private final LocalDate weekStart;
  private final LocalDate weekEnd;
  private final DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private final DateTimeFormatter monthDayYearFormatter = DateTimeFormatter.ofPattern(
      "EEE, MMMM dd, yyyy");
  private final DateTimeFormatter shortFormatter = DateTimeFormatter.ofPattern("MMM-dd");

  public static void main(String[] args) {
    String outputFolder = "src/test/resources";

    // HERE INPUT START AND END NUMBER OF WEEK. THEN -- YEAR
    IntStream.rangeClosed(26, 26).forEach(num -> {
      GenerateWeekTemplate generator = new GenerateWeekTemplate(2025, num);
      generator.writeTemplateToFile(outputFolder);
    });
  }

  public GenerateWeekTemplate(int year, int weekNumber) {
    WeekFields weekFields = WeekFields.ISO;
    this.weekStart = LocalDate.of(year, 1, 1)
        .with(weekFields.weekOfWeekBasedYear(), weekNumber)
        .with(weekFields.dayOfWeek(), 1);
    this.weekEnd = weekStart.plusDays(6);
  }

  public GenerateWeekTemplate(LocalDate weekStart, LocalDate weekEnd) {
    this.weekStart = weekStart;
    this.weekEnd = weekEnd;
  }

  public String generateTemplate() {
    StringBuilder template = new StringBuilder();

    // Header
    template.append("---\n");
    template.append("journal: week\n");
    template.append(String.format("journal-start-date: %s\n", weekStart.format(fullFormatter)));
    template.append(String.format("journal-end-date: %s\n", weekEnd.format(fullFormatter)));
    template.append("journal-section: week\n");
    template.append("tags:\n");
    template.append("  - \"#time-date/regular/weekly\"\n");
    template.append("---\n");

    // Calendar timeline
    template.append("```calendar-timeline\n");
    template.append("mode: month\n");
    template.append("```\n\n");

    // Week table
    template.append(generateWeekTable());

    // TODO section
    template.append("---\n");
    template.append("#### TODO\n");
    template.append("```dataview\n");
    template.append("TASK\n");
    template.append("FROM \"/\"\n");
    template.append("WHERE !completed \n");
    template.append(String.format("AND scheduled >= date(%s) AND scheduled <= date(%s)\n",
        weekStart.format(fullFormatter), weekEnd.format(fullFormatter)));
    template.append("AND !contains(tags, \"#label/brand/obsidian\")\n");
    template.append("SORT scheduled ASC\n");
    template.append("```\n");

    // Daily sections (in reverse order)
    LocalDate currentDate = weekEnd;
    while (!currentDate.isBefore(weekStart)) {
      template.append("---\n");
      template.append(String.format("### %s\n", currentDate.format(monthDayYearFormatter)));
      template.append("[TBD]\n\n");
      currentDate = currentDate.minusDays(1);
    }

    // Final separator and newlines
    template.append("---\n\n\n\n\n\n\n\n\n");

    return template.toString();
  }

  private String generateWeekTable() {
    StringBuilder table = new StringBuilder();

    // Header row
    table.append(
        "|              Mon               |              Tue               |               Wed                |              Thu               |               Fri                |               Sat               |               Sun               |\n");

    // Separator row
    table.append(
        "|:------------------------------:|:------------------------------:|:--------------------------------:|:------------------------------:|:--------------------------------:|:-------------------------------:|:-------------------------------:|\n");

    // Date row
    table.append("|");
    LocalDate current = weekStart;
    for (int i = 0; i < 7; i++) {
      String formattedDate = String.format("[[#%s\\|%s]]",
          current.format(monthDayYearFormatter),
          current.format(shortFormatter));
      table.append(String.format(" %-30s|", formattedDate));
      current = current.plusDays(1);
    }
    table.append("\n\n");

    return table.toString();
  }

  public void writeTemplateToFile(String outputDirectory) {
    // Create filename using the week start date
    String fileName = String.format("%s-W%02d.md",
        weekStart.getYear(),
        weekStart.get(WeekFields.ISO.weekOfWeekBasedYear()));

    Path filePath = Path.of(outputDirectory, fileName);

    try {
      // Create directories if they don't exist
      Files.createDirectories(filePath.getParent());

      // Write the template to file
      try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
        writer.write(generateTemplate());
      }

      System.out.println("Template successfully written to: " + filePath);

    } catch (IOException e) {
      System.err.println("Error writing template to file: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
