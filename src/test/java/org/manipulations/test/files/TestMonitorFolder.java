package org.manipulations.test.files;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.manipulations.file.monitor.WatchFolderService;

public class TestMonitorFolder {

  @Test
  public void monitorFolder() throws IOException, InterruptedException {

    WatchFolderService.monitorPropertyFolder();

  }

}
