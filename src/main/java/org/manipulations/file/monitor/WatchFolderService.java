package org.manipulations.file.monitor;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchFolderService {

  // monitor changes in directory
  public static void monitorPropertyFolder() throws IOException, InterruptedException {

    WatchService monitorFolder = FileSystems.getDefault().newWatchService();
    Path pathFolder = Paths.get("src/main/resources/logs/data01/");

    pathFolder.register(monitorFolder
        , StandardWatchEventKinds.ENTRY_CREATE
        , StandardWatchEventKinds.ENTRY_MODIFY
        , StandardWatchEventKinds.ENTRY_DELETE);

    boolean poll = true;

    WatchKey key = monitorFolder.take();

    while (poll) {
      for (WatchEvent<?> event : key.pollEvents()) {
        System.out.println(event.kind() + ": "
            + System.currentTimeMillis()
            + " " + event.context());
      }

      poll = key.reset();
    }
  }
}
