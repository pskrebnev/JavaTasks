package org.tasks.datetime;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Test class demonstrating various ways to get current date and time
 */
public class TestDateTime {

    @Test
    public void testCurrentDateLegacy() {
        System.out.println("=== Testing Legacy Date API ===");
        Date currentDate = DateTimeUtils.getCurrentDateLegacy();
        System.out.println("Current Date (Legacy): " + currentDate);
        System.out.println();
    }

    @Test
    public void testCurrentCalendar() {
        System.out.println("=== Testing Calendar API ===");
        Calendar currentCalendar = DateTimeUtils.getCurrentCalendar();
        System.out.println("Current Calendar: " + currentCalendar.getTime());
        System.out.println("Year: " + currentCalendar.get(Calendar.YEAR));
        System.out.println("Month: " + (currentCalendar.get(Calendar.MONTH) + 1)); // +1 because months are 0-based
        System.out.println("Day: " + currentCalendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("Hour: " + currentCalendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("Minute: " + currentCalendar.get(Calendar.MINUTE));
        System.out.println("Second: " + currentCalendar.get(Calendar.SECOND));
        System.out.println();
    }

    @Test
    public void testModernDateTimeAPI() {
        System.out.println("=== Testing Modern Date/Time API (Java 8+) ===");
        
        LocalDate currentDate = DateTimeUtils.getCurrentLocalDate();
        LocalTime currentTime = DateTimeUtils.getCurrentLocalTime();
        LocalDateTime currentDateTime = DateTimeUtils.getCurrentLocalDateTime();
        ZonedDateTime currentZonedDateTime = DateTimeUtils.getCurrentZonedDateTime();
        
        System.out.println("Current LocalDate: " + currentDate);
        System.out.println("Current LocalTime: " + currentTime);
        System.out.println("Current LocalDateTime: " + currentDateTime);
        System.out.println("Current ZonedDateTime: " + currentZonedDateTime);
        System.out.println("Timezone: " + currentZonedDateTime.getZone());
        System.out.println();
    }

    @Test
    public void testFormattedDateTime() {
        System.out.println("=== Testing Formatted Date/Time ===");
        
        String formattedDate = DateTimeUtils.getCurrentDateFormatted();
        String formattedTime = DateTimeUtils.getCurrentTimeFormatted();
        String formattedDateTime = DateTimeUtils.getCurrentDateTimeFormatted();
        
        System.out.println("Formatted Date (yyyy-MM-dd): " + formattedDate);
        System.out.println("Formatted Time (HH:mm:ss): " + formattedTime);
        System.out.println("Formatted DateTime (yyyy-MM-dd HH:mm:ss): " + formattedDateTime);
        System.out.println();
    }

    @Test
    public void testTimestamps() {
        System.out.println("=== Testing Timestamps ===");
        
        long millisTimestamp = DateTimeUtils.getCurrentTimestamp();
        long nanoTimestamp = DateTimeUtils.getCurrentNanoTime();
        
        System.out.println("Current timestamp (milliseconds): " + millisTimestamp);
        System.out.println("Current timestamp (nanoseconds): " + nanoTimestamp);
        System.out.println("Readable date from millis: " + new Date(millisTimestamp));
        System.out.println();
    }

    @Test
    public void testAllDateTimeMethods() {
        System.out.println("=== Comprehensive Date/Time Demonstration ===");
        DateTimeUtils.demonstrateAll();
        System.out.println();
        System.out.println("All date/time methods executed successfully!");
    }

    @Test
    public void testCurrentDateTimeForIssue() {
        System.out.println("=== Current Date and Time for Issue ===");
        System.out.println("The current date and time is: " + DateTimeUtils.getCurrentDateTimeFormatted());
        System.out.println("Using LocalDateTime.now(): " + DateTimeUtils.getCurrentLocalDateTime());
        System.out.println("Using Date(): " + DateTimeUtils.getCurrentDateLegacy());
        System.out.println();
        System.out.println("This demonstrates multiple ways to get current date and time in Java!");
    }
}