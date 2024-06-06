package org.interfaces.tests;

import java.util.function.IntPredicate;
import org.interfaces.lambda.Evaluate;
import org.junit.jupiter.api.Test;

public class TestInterfaces {

  @Test
  public void testInterfaceNegative() {
    Evaluate<Integer> number = i -> i < 0;
    IntPredicate moreThanFour = n -> n >= 4;

    System.out.println("Number -1 is negative = " + number.isNegative(-1));
    System.out.println("Number 3 is more than 4 = " + moreThanFour.test(3));
    System.out.println("Number 5 is more than 4 = " + moreThanFour.test(5));
  }



}
