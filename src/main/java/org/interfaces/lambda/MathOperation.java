package org.interfaces.lambda;

@FunctionalInterface
public interface MathOperation<T> {
  T sum(T t, T v);
}


