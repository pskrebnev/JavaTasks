package org.tasks.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility class demonstrating various ways to get current date and time in Java
 */
public class DateTimeUtils {

    /**
     * Get current date and time using legacy java.util.Date
     * @return Current date and time as Date object
     */
    public static Date getCurrentDateLegacy() {
        return new Date();
    }

    /**
     * Get current date and time using java.util.Calendar
     * @return Current date and time as Calendar object
     */
    public static Calendar getCurrentCalendar() {
        return Calendar.getInstance();
    }

    /**
     * Get current local date using java.time.LocalDate (Java 8+)
     * @return Current local date
     */
    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    /**
     * Get current local time using java.time.LocalTime (Java 8+)
     * @return Current local time
     */
    public static LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    /**
     * Get current local date and time using java.time.LocalDateTime (Java 8+)
     * @return Current local date and time
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * Get current zoned date and time using java.time.ZonedDateTime (Java 8+)
     * @return Current zoned date and time with system default timezone
     */
    public static ZonedDateTime getCurrentZonedDateTime() {
        return ZonedDateTime.now();
    }

    /**
     * Get current date and time as formatted string
     * @return Current date and time formatted as "yyyy-MM-dd HH:mm:ss"
     */
    public static String getCurrentDateTimeFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    /**
     * Get current date as formatted string
     * @return Current date formatted as "yyyy-MM-dd"
     */
    public static String getCurrentDateFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.now().format(formatter);
    }

    /**
     * Get current time as formatted string
     * @return Current time formatted as "HH:mm:ss"
     */
    public static String getCurrentTimeFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalTime.now().format(formatter);
    }

    /**
     * Get current timestamp in milliseconds
     * @return Current timestamp in milliseconds since epoch
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * Get current timestamp in nanoseconds
     * @return Current timestamp in nanoseconds
     */
    public static long getCurrentNanoTime() {
        return System.nanoTime();
    }

    /**
     * Demonstrate all date/time methods
     */
    public static void demonstrateAll() {
        System.out.println("=== Current Date and Time Demonstration ===");
        System.out.println();
        
        System.out.println("Legacy Date API:");
        System.out.println("Date: " + getCurrentDateLegacy());
        System.out.println("Calendar: " + getCurrentCalendar().getTime());
        System.out.println();
        
        System.out.println("Modern Date/Time API (Java 8+):");
        System.out.println("LocalDate: " + getCurrentLocalDate());
        System.out.println("LocalTime: " + getCurrentLocalTime());
        System.out.println("LocalDateTime: " + getCurrentLocalDateTime());
        System.out.println("ZonedDateTime: " + getCurrentZonedDateTime());
        System.out.println();
        
        System.out.println("Formatted Date/Time:");
        System.out.println("Date formatted: " + getCurrentDateFormatted());
        System.out.println("Time formatted: " + getCurrentTimeFormatted());
        System.out.println("DateTime formatted: " + getCurrentDateTimeFormatted());
        System.out.println();
        
        System.out.println("Timestamps:");
        System.out.println("Milliseconds: " + getCurrentTimestamp());
        System.out.println("Nanoseconds: " + getCurrentNanoTime());
    }
}