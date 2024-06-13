package org.tasks.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAssignment {

  public static void main(String[] args) {
//    task01();
//    task02();
//    task03();
//    task04();

//    Optional<String> grade1 = getGrade(50);
//    Optional<String> grade2 = getGrade(55);
//    Consumer<String> cons = System.out::println;
//
//    System.out.println(grade1.orElse("UNKNOWN"));
//    if (grade2.isPresent()) {
//      grade2.ifPresent(cons);
//    } else {
//      grade2.orElse("Empty");
//    }

//    task06();
//    task07();
//    task08();
//    task09();
//    task10();
//    task11();
//    task12();
    task13();
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

    if (marks > 50) {
      grade = Optional.of("PASS");
    } else {
      grade.of("FAIL");
    }

    return grade;
  }

  // QID 2.1809
  private static void task06() {
    List<Book> books = Arrays.asList(
        new Book("Thinking in Java", 30.0),
        new Book("Java in 24 hrs", 20.0),
        new Book("Java Recipes", 10.0)
    );

    Predicate<Book> price10 = book -> book.getPrice() > 10;
    Predicate<Book> price90 = book -> book.getPrice() > 90;

    double pr = books.stream()
        .filter(price90)
        .mapToDouble(Book::getPrice)
        .average()
        .orElse(0.00);

    System.out.println("The avg price of books = " + pr);
  }

  // QID 2.1846
  private static void task07() {
    List<Book> books = Arrays.asList(
        new Book("Atlas Shrugged", 10.0),
        new Book("Freedom at Midnight", 5.0),
        new Book("Gone with the wind", 5.0)
    );

    Predicate<Entry<String, Double>> startWith = book -> book.getKey().startsWith("A");

    books.stream()
        .collect(Collectors.toMap(
            Book::getTitle,
            Book::getPrice
        ))
        .entrySet().stream()
        .filter(startWith)
        .forEach(book -> System.out.println("The price for books started with 'A' = "
            + book.getValue()));
  }

  // QID 2.1847
  private static void task08() {
    List<Book> books = Arrays.asList(
        new Book("Gone with the wind", 5.0),
        new Book("Gone with the wind", 10.0),
        new Book("Atlas Shrugged", 15.0)
    );

//    books.stream()
//        .collect(Collectors.toMap(
//            Book::getTitle,
//            Book::getPrice,
//            Double::sum, LinkedHashMap::new
//        ))
//        .entrySet()
//        .forEach(System.out::println);

    books.stream()
        .collect(Collectors.toMap(
            book -> book.getTitle() + ":" + book.getPrice(),
            Function.identity(),
            (existing, newValue) -> existing,
            LinkedHashMap::new
        ))
        .entrySet()
        .forEach(System.out::println);
  }

  // QID 2.1810
  private static void task09() {
    List<Person> persons = Arrays.asList(
        new Person("Bob", 31),
        new Person("Paul", 32),
        new Person("John", 33)
    );

    Predicate<Person> pAge = person -> person.getAge() < 30;

    double averageAge = persons.stream()
        .filter(pAge)
        .mapToInt(Person::getAge)
        .average()
        .orElse(0.00);

    System.out.println("The average age is " + averageAge);
  }

  // QID 2.1849
  private static void task10() {
    Optional<Double> price = Optional.ofNullable(20.0);
    price.ifPresent(y -> System.out.println("1.Price is: " + y));
    System.out.println("2.Price is: " + price.orElse(0.00));
    System.out.println("3.Price is: " + price.orElseGet(() -> null));

    System.out.println("<--->");

    Optional<Double> price2 = Optional.ofNullable(null);
    System.out.println("1.Price is: " + price2);
    System.out.println("2.Price is: " + (price2.isEmpty() ? "empty" : 20.00));
    price2.ifPresent(y -> System.out.println("3.Price is: " + y));
    Double pr = price2.orElse(44.0);
    System.out.println("4.Price is: " + pr);

    Optional<Double> price3 = Optional.ofNullable(null);
    Double z = price3.orElseThrow(() -> new RuntimeException("Bad Code"));
    System.out.println("5. Price is: " + z); // RuntimeException thrown
  }

  // QID 2.1858
  private static void task11() {
    List<AnotherBook> books = Arrays.asList(
        new AnotherBook("Gone with the wind", "Fiction"),
        new AnotherBook("Bourne Ultimatum", "Thriller"),
        new AnotherBook("The Client", "Thriller")
    );

    List<String> genreList = new ArrayList<>();

    books.stream()
        .forEach(book -> {
          genreList.add(book.getGenre());
        });

    genreList.forEach(System.out::println);
  }

  // QID 2.2024
  private static void task12() {
    // a)
    DoublePredicate isOdd = x -> x % 2 != 0;
    DoublePredicate isEven = x -> x % 2 == 0;

    double s = DoubleStream.of(0, 2, 4)
        .filter(isOdd)
        .sum();

    System.out.println("Sum is: " + s);

    // b)
    Stream<Double> dbl = Stream.of(1.0, 3.0);
    double dd = dbl.mapToDouble(x -> (double) x)
        .filter(isEven)
        .average()
        .orElse(0.00);

    System.out.println("The average is: " + dd);
  }

  // QID 2.1840
  private static void task13() {
    // a)
    List<Integer> ls = Arrays.asList(11, 11, 22, 33, 33, 55, 66);
    Predicate<Integer> is11 = x -> x == 11;
    Predicate<Integer> mod11 = x -> x % 11 > 0;


    ls.stream()
        .distinct()
        .forEach(System.out::println);

    boolean find11 = ls.stream()
        .distinct()
        .anyMatch(is11);

    System.out.println("Is list contain 11? -> " + find11);

    boolean b11 = ls.stream()
        .noneMatch(mod11);
    System.out.println("Is divided by 11? -> " + b11);
  }

}


