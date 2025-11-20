package org.udemy.lab01;

@FunctionalInterface
public interface Printable<T> {

  void print(T t);
}
