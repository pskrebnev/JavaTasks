package org.tasks.execution;

@FunctionalInterface
public interface Printable<T> {
  void print(T t);
}
