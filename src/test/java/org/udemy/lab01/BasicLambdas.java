package org.udemy.lab01;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BasicLambdas {

  public static void main(String[] args) {
    BasicLambdas demo = new BasicLambdas();
    demo.consumer();
    demo.supplier();
    demo.predicate();
    demo.function();

    List<Person> listPeople = demo.getPeople();

    demo.sortName(listPeople);
    listPeople.forEach(p -> System.out.println(p.toString()));

    demo.sortHeight(listPeople);
    listPeople.forEach(p -> System.out.println(p.toString()));

    demo.sortAge(listPeople);
    listPeople.forEach(p -> System.out.println(p.toString()));
  }

  public void consumer() {
    Printable<String> printableLambda = System.out::println;
    printableLambda.print("Printable lambda");

    Consumer<String> consumerLambda = p -> System.out.println(p);
    consumerLambda.accept("Consumer lambda");

    Consumer<String> consumerMr = System.out::println;
    consumerMr.accept("Consumer method reference");
  }

  public void supplier() {
    Retrievable<Integer> callInt = () -> 77;
    System.out.println("Method supplier. Retrievable = " + callInt.retrieve());

    Supplier<Integer> callIntSup = () -> 77;
    System.out.println("Method supplier. Supplier = " + callIntSup.get());
  }

  public void predicate() {
    Evaluate<Integer> eval = n -> n < 0;
    System.out.println("From eval (-1) = " + eval.isNegative(-1));
    System.out.println("From eval (1) = " + eval.isNegative(1));

    Predicate<Integer> isNegative = n -> n < 0;
    System.out.println("From predicate (-1) = " + isNegative.test(-1));
    System.out.println("From predicate (1) = " + isNegative.test(1));

    System.out.println("Is 4 even -> " + check(4, n -> n % 2 == 0));
    System.out.println("Is 7 even -> " + check(7, n -> n % 2 == 0));

    System.out.println("Is 'Mr. Joe Bloggs' start with 'Mr.' -> "
        + check("Mr. Joe Bloggs", s -> s.startsWith("Mr.")));
    System.out.println("Is 'Ms. Ann Bloggs' start with 'Mr.' -> "
        + check("Ms. Ann Bloggs", s -> s.startsWith("Mr.")));

    Person p1 = new Person("Mike", 33, 1.8);
    Person p2 = new Person("Ann", 13, 1.4);

    System.out.println("Is 'Mike' an adult person -> "
        + check(p1, p -> p.getAge() >= 18));
    System.out.println("Is 'Ann' an adult person -> "
        + check(p2, p -> p.getAge() >= 18));
  }

  public void function() {
    Functionable<Integer, String> printNum = n -> "Number is: " + n;
    System.out.println("From 'Functionable' -> " + printNum.applyThis(25));

    Function<Integer, String> printNumFunc = n -> "Number is: " + n;
    System.out.println("From 'Function' -> " + printNumFunc.apply(25));
  }

  public <T> boolean check(T value, Predicate<T> predicate) {
    return predicate.test(value);
  }

  private void sortName(List<Person> personList) {
    personList
        .sort(Comparator.comparing(Person::getName));
  }

  private void sortAge(List<Person> personList) {
    personList
        .sort(Comparator.comparing(Person::getAge));
  }

  private void sortHeight(List<Person> personList) {
    personList
        .sort(Comparator.comparing(Person::getHeight));
  }

  private List<Person> getPeople() {
    return Arrays.asList(
        new Person("Ann", 28, 1.63),
        new Person("Dory", 48, 1.85),
        new Person("Jack", 27, 1.95),
        new Person("Mike", 33, 1.8),
        new Person("Mary", 25, 1.4),
        new Person("Alan", 34, 1.7),
        new Person("Zoe", 30, 1.5)
    );
  }
}
