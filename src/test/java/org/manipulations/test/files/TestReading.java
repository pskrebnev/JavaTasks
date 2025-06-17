package org.manipulations.test.files;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class TestReading {

  String text = "How do you split a string in Java?\n"
      + "You can use split(String regex) to split the String into a String array based on"
      + " the provided regular expression.\n"
      + "\n"
      + "Why is a character array preferred over String for storing passwords in Java?\n"
      + "A String object is immutable in Java and is stored in the string pool. Once"
      + " it’s created it stays in the pool until garbage collection completes, so"
      + " even though you’re done with the password it’s available in memory for"
      + " longer duration. It’s a security risk because anyone having access to memory"
      + " dump can find the password as clear text. If you use a character array to"
      + " store password, you can set it to blank once you’re done with it. You can"
      + " control for how long it’s available in memory and that avoids the security threat.\n"
      + "\n"
      + "How do you check if two Strings are equal in Java?\n"
      + "There are two ways to check if two Strings are equal. You can use the == operator"
      + " or the equals() method. When you use the == operator, it checks for the"
      + " value of String as well as the object reference. Often in Java programming"
      + " you want to check only for the equality of the String value. In this case,"
      + " you should use the equals() method to check if two Strings are equal."
      + " There is another function called equalsIgnoreCase that you can use to ignore case.";
  String toFind = "te";

  @Test
  public void testReading() {

    int freq = cleanText(text).chars()
        .mapToObj(c -> (char) c)
        .map(String::valueOf)
        .collect(Collectors.joining())
        .split(toFind)
        .length - 1;

    System.out.println("Phrase 'te' appears '" + freq + "' times.");
  }

  @Test
  public void testWords() {
    Arrays.stream(cleanText(text).split(" "))
        .toList()
        .stream()
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ))
        .entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .limit(20)
        .forEach(item -> System.out.println(item.getKey() + " = " + item.getValue()));
  }

  private String cleanText(String st) {
    return Pattern.compile("[^a-zA-Z]")
        .matcher(st)
        .replaceAll(" ")
        .toLowerCase()
        .replaceAll(" +", " ");
  }
}
