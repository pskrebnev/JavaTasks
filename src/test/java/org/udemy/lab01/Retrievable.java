package org.udemy.lab01;

@FunctionalInterface
public interface Retrievable<T> {

  T retrieve();
}
