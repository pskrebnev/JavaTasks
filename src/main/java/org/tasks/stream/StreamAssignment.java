package org.tasks.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAssignment {

  public static void main(String[] args) {
//    task01();
//    task02();
//    task03();
    task04();
  }

  // QID 2.2023
  private static void task01() {
    System.out.println(IntStream.range(0, 5)
        .average()
        .orElse(Double.NaN));
  }

  // QID Q2.1762
  private static void task02() {
    List<Item> items = Arrays.asList(
        new Item(1, "Screw"),
        new Item(2, "Nail"),
        new Item(3, "Bolt")
    );

    items.stream()
        .sorted(Comparator.comparingInt(Item::getId).reversed())
        .forEach(item -> System.out.print(item.getName()));
  }

  // QID 2.1787
  private static void task03() {
    Stream<List<String>> str = Stream.of(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
    Predicate<List<String>> isCHere = list -> list.contains("c");

    str.filter(isCHere)
        .flatMap(Collection::stream)
        .forEach(System.out::println);
  }

  // QID 2.1738
  private static void task04() {
    // a)
    System.out.println("The sum = " + IntStream.rangeClosed(1, 3).sum());
    System.out.println("The max = " + IntStream.rangeClosed(1, 3).max().orElseThrow());

    // b)
    List<Person> persons = Arrays.asList(
        new Person("Alan", "Burke", 22),
        new Person("Zoe", "Peters", 20),
        new Person("Peter", "Castle", 29)
    );

    Person person = persons.stream()
        .max(Comparator.comparingInt(Person::getAge))
        .get();

    System.out.println(person);

    // c)
    List<Integer> ints = Arrays.asList(10, 47, 33, 23);

    System.out.println("i -> " + ints.stream()
        .reduce(Math::max).get());

    System.out.println("ii -> " + ints.stream()
        .reduce(Integer.MIN_VALUE, Math::max));
  }

  // QID 2.1826, task #5
  public static Optional<String> getGrade(int marks) {

    Optional<String> grade = Optional.empty();

    return null;

  }




}
