package org.tasks.execution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

// Tasks from 'Java 21, Java 17, Java 11 and Advanced Java 8' training (on Udemy)
// link: https://luxoft.udemy.com/course/ocp11_from_oca8/
public class LambdasAndMethodReferences {

  public static void main(String[] args) {
    List<Person> listPeople = getPeople();

//    consumer();
//    supplier();
//    predicate();
//    function();
//    staticMR();
//    boundMR();
//    unboundMR();
//    constructorMR();
  }

  public static void supplier() {
    Retrievable getBack = () -> 77;
    System.out.println("With 'Retr': " + getBack.retrieve());

    Supplier<Integer> print77Lambda = () -> {
      System.out.println("Lambda: " + 77);
      return 0;
    };

    System.out.println("With 'Supplier': " + print77Lambda.get());
  }

  public static void predicate() {
    Evaluate<Integer> isNegative = num -> num < 0 ? true : false;
    Predicate<Integer> isNumberEven = n -> n % 2 == 0;
    Predicate<String> isStartedMr = str -> str.startsWith("Mr.");
    Predicate<Integer> isPersonAdult = num -> num >= 18;

    System.out.println("Lamd: '-1': " + isNegative.eval(-1));
    System.out.println("Lamd: '+1': " + isNegative.eval(1));

    IntPredicate isMoreZero = num -> num < 0;
    System.out.println("Lambd 'isMoreZero': '-1': " + isMoreZero.test(-1));
    System.out.println("Lambd 'isMoreZero': '+1': " + isMoreZero.test(1));

    Matcheble<Integer, Predicate> isEven = (num, condition) -> condition.test(num);
    System.out.println("Lambd 'isEven': '4': " + isEven.check(4, isNumberEven));
    System.out.println("Lambd 'isEven': '7': " + isEven.check(7, isNumberEven));

    Matcheble<String, Predicate> isStarted = (str, cond) -> cond.test(str);
    System.out.println("Started with 'Mr.': 'Mr. Joe Bloggs': "
        + isStarted.check("Mr. Joe Bloggs", isStartedMr));
    System.out.println("Started with 'Mr.': 'Ms. Ann Bloggs': "
        + isStarted.check("Ms. Ann Bloggs", isStartedMr));

    Matcheble<Integer, Predicate> isAdult = (i, condition) -> condition.test(i);
    System.out.println("Person adult?: 'Mike, 33': "
        + isAdult.check(33, isPersonAdult));
    System.out.println("Person adult?: 'Ann, 13': "
        + isAdult.check(13, isPersonAdult));
  }

  public static void function() {
    Functionable append = n -> "Number is: ".concat(String.valueOf(n));
    Function<Integer, String> appendFun = n -> "Number is: ".concat(String.valueOf(n));

    System.out.println("With custom Interface");
    System.out.println(append.funct(25));

    System.out.println("With custom Function");
    System.out.println(appendFun.apply(25));
  }

  public static void consumer() {
    Printable<String> printableLambda = s -> System.out.println(s);
    printableLambda.print("Printable lambda");
  }

  public static void staticMR() {
    List<Integer> listInt = Arrays.asList(1, 2, 7, 4, 5);
    Consumer<List<Integer>> sortListLambda = list -> Collections.sort(list);

    System.out.println("Before sorting: " + listInt);

    sortListLambda.accept(listInt);
    System.out.println("After sorting: " + listInt);

    Collections.shuffle(listInt);
    System.out.println("Before sorting: " + listInt);

    Consumer<List<Integer>> sortListMR = Collections::sort;
    sortListMR.accept(listInt);
    System.out.println("After sorting: " + listInt);
  }

  public static void boundMR() {

    String name = "Mr. Joe Bloggs";

    Predicate<String> isStartedWithLambda = pre -> name.startsWith(pre);

    System.out.println("Started with ? 'Mr.' = " + isStartedWithLambda.test("Mr."));
    System.out.println("Started with ? 'Ms.' = " + isStartedWithLambda.test("Ms."));

    Predicate<String> isStartedWithMR = name::startsWith;

    System.out.println("Started with ? 'Mr.' = " + isStartedWithMR.test("Mr."));
    System.out.println("Started with ? 'Ms.' = " + isStartedWithMR.test("Ms."));
  }

  public static void unboundMR() {
    String pre1 = "Mr.";
    String pre2 = "Ms.";
    String name = "Mr. Joe Bloggs";

    Predicate<String> isEmptyLambda = s -> s.isEmpty();

    System.out.println("Text empty? = " + isEmptyLambda.test(""));
    System.out.println("Text empty? = " + isEmptyLambda.test("xyz"));

    Predicate<String> isEmptyMR = String::isEmpty;

    System.out.println("Text empty? = " + isEmptyMR.test(""));
    System.out.println("Text empty? = " + isEmptyMR.test("xyz"));

    BiPredicate<String, String> startsWithLambda = (s1, s2) -> s1.startsWith(s2);

    System.out.println("Is started? (L) = " + startsWithLambda.test(name, pre1));
    System.out.println("Is started? (L) = " + startsWithLambda.test(name, pre2));

    BiPredicate<String, String> startsWithMR = String::startsWith;

    System.out.println("Is started? (MR) = " + startsWithMR.test(name, pre1));
    System.out.println("Is started? (MR) = " + startsWithMR.test(name, pre2));
  }

  public static void constructorMR() {
    String lambda = "Lambda";
    String mRef = "Method Reference";

    Supplier<List<String>> suppListLambda = () -> new ArrayList<>();

    List<String> newList = suppListLambda.get();
    newList.add(lambda);

    System.out.println("With L: " + newList);

    // method reference
    Supplier<List<String>> suppListMR = ArrayList::new;
    newList = suppListMR.get();
    newList.add(mRef);

    System.out.println("With MR: " + newList);

    Function<Integer, List<String>> getListWithLambda = n -> new ArrayList<>(n);
    newList = getListWithLambda.apply(10);
    newList.add(lambda);

    System.out.println("With L + F:" + newList);

    // method reference
    Function<Integer, List<String>> getListWithMR = ArrayList::new;
    List<String> anotherList = getListWithMR.apply(20);
    anotherList.add(mRef);

    System.out.println("With MR + F:" + anotherList);
  }

  private static List<Person> getPeople() {
    List<Person> result = new ArrayList<>();
    result.add(new Person("Mike", 33, 1.8));
    result.add(new Person("Mary", 25, 1.4));
    result.add(new Person("Alan", 34, 1.7));
    result.add(new Person("Zoe", 30, 1.5));
    return result;
  }
}
