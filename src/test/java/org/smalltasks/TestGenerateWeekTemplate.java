package org.smalltasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class TestGenerateWeekTemplate {
    public static void main(String[] args) throws IOException {
        // Create a generator for a specific week
        GenerateWeekTemplate generator = new GenerateWeekTemplate(2025, 25);
        
        // Generate the template
        String template = generator.generateTemplate();
        
        // Print the template to verify the link format
        System.out.println("Generated template:");
        System.out.println("------------------");
        System.out.println(template);
        
        // Write to a test file
        String outputFolder = "src/test/resources";
        generator.writeTemplateToFile(outputFolder);
        
        System.out.println("Template file generated successfully.");
    }
}