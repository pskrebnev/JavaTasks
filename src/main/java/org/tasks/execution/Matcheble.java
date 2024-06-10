package org.tasks.execution;

import java.util.function.Predicate;

public interface Matcheble<T, P> {

  boolean check(T t, Predicate<T> p);

}
