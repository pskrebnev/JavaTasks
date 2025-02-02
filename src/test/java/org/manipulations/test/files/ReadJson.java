package org.manipulations.test.files;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadJson {

  String filePath = "src/test/resources";
  String fileName = "data.json";

  public static void main(String[] args) {
    try {
      // Create Gson instance
      Gson gson = new Gson();

      // Create reader
      Reader reader = Files.newBufferedReader(Paths.get("path/to/your/file.json"));

      // Parse JSON file
      JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

      // Read simple value
      String value = jsonObject.get("keyName").getAsString();

      // Read nested value
      String nestedValue = jsonObject.getAsJsonObject("parent")
          .get("child")
          .getAsString();

      // Read array
      JsonArray array = jsonObject.getAsJsonArray("arrayKey");
      array.forEach(element -> System.out.println(element.getAsString()));

      // Close reader
      reader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

