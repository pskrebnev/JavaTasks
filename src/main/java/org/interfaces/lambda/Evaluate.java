package org.interfaces.lambda;

@FunctionalInterface
public interface Evaluate<T> {
  boolean isNegative(T t);
}
