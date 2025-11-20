package org.interfaces.lambda;

@FunctionalInterface
public interface CompareWith<T, U> {

  boolean compareWith(T t, U u);
}
