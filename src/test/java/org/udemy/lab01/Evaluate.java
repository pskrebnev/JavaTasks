package org.udemy.lab01;

@FunctionalInterface
public interface Evaluate<T> {

  boolean isNegative(T t);
}
