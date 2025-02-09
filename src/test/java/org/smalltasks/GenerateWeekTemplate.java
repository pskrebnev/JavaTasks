package org.smalltasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class GenerateWeekTemplate {

  private final LocalDate weekStart;
  private final LocalDate weekEnd;
  private final DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private final DateTimeFormatter monthDayYearFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
  private final DateTimeFormatter shortFormatter = DateTimeFormatter.ofPattern("MMM-dd");

  public static void main(String[] args) {
    GenerateWeekTemplate generator = new GenerateWeekTemplate(2025, 7); // For the week containing Feb 3, 2025
    String template = generator.generateTemplate();
    System.out.println(template);
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
    table.append("|              Mon               |              Tue               |               Wed                |              Thu               |               Fri                |               Sat               |               Sun               |\n");

    // Separator row
    table.append("|:------------------------------:|:------------------------------:|:--------------------------------:|:------------------------------:|:--------------------------------:|:-------------------------------:|:-------------------------------:|\n");

    // Date row
    table.append("|");
    LocalDate current = weekStart;
    for (int i = 0; i < 7; i++) {
      String formattedDate = String.format("[[#%s\\|%s]]",
          current.format(monthDayYearFormatter),
          current.format(DateTimeFormatter.ofPattern("MMM-dd")));
      table.append(String.format(" %-30s|", formattedDate));
      current = current.plusDays(1);
    }
    table.append("\n\n");

    return table.toString();
  }
}
