package org.interfaces.tests;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.interfaces.lambda.Evaluate;
import org.junit.jupiter.api.Test;

public class TestInterfaces {

  String txt = "Instead of starting with SELECT, a DQL query starts with a word determining the"
      + " Query Type, which determines how your final result will be rendered on screen (a table,"
      + " a list, a task list, or a calendar). Then follows the list of fields, which is actually"
      + " very similar to the column list you put after a SELECT statement.\n"
      + "\n"
      + "The next line starts with FROM which is not followed by a table name but by a complex"
      + " expression, similar to an SQL WHERE clause. Here you can filter on many things, like"
      + " tags in files, file names, path names, etc. In the background, this command already"
      + " produces a result set which will be our initial set for further data manipulation by"
      + " 'data commands' on subsequent lines.\n"
      + "\n"
      + "You can have as many following lines as you want. Each will start with a data command"
      + " and will re-shape the result set it received from the previous line. For example:\n"
      + "\n"
      + "The WHERE data command will only keep those lines from the result set which match a"
      + " given condition. This means that, unless all data in the result set matches the"
      + " condition, this command will pass on a smaller result set to the next line than it"
      + " received from the previous line. Unlike in SQL, you can have as many WHERE"
      + " commands as you like.\n"
      + "The FLATTEN data command is not found in common SQL but in DQL you can use it to"
      + " reduce the depth of the result set.\n"
      + "DQL, similarly to SQL, has a GROUP BY command but this can also be used multiple"
      + " times, which is not possible in common SQL. You can even do several SORT or GROUP BY"
      + " commands one after the other.";

  @Test
  public void testInterfaceNegative() {
    Evaluate<Integer> number = i -> i < 0;
    IntPredicate moreThanFour = n -> n >= 4;

    System.out.println("Number -1 is negative = " + number.isNegative(-1));
    System.out.println("Number 3 is more than 4 = " + moreThanFour.test(3));
    System.out.println("Number 5 is more than 4 = " + moreThanFour.test(5));
  }

  @Test
  public void testFindLargest() {
    // Map<String, Long> where
    // String is a 'word'
    // and Long is a 'length' of the word
    // long words on a top. For ex.:
    // Map('manipulation'=12, 'determining'=11)
    Arrays.stream(keepOnlyWords(txt).split(" "))
        .collect(Collectors.toMap(
            Function.identity(),
            String::length,
            (oldValue, newValue) -> oldValue, LinkedHashMap::new
        ))
        .entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .limit(20)
        .forEach(System.out::println);
  }

  @Test
  public void testCountLength() {
    // count the length of words and their appearing in text
    // for example: words with length of 4 appear 6 times in this text
    // Map<4, 6>
    // sorted by key
    Arrays.stream(keepOnlyWords(txt).split(" "))
        .map(String::length)
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ))
        .entrySet().stream()
        .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
        .forEach(System.out::println);
  }

  // clear text and leave just words in lower case
  private String keepOnlyWords(String input) {
    return Pattern.compile("[^a-zA-Z]")
        .matcher(input)
        .replaceAll(" ")
        .trim()
        .replaceAll(" +", " ")
        .toLowerCase();
  }
}
