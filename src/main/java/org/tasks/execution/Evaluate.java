package org.tasks.execution;

@FunctionalInterface
public interface Evaluate<T> {

  boolean eval(T t);
}
