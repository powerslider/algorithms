package com.ceco.algorithms.datastructure.list;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 12 Feb 2015
 */
public interface List<T> extends Iterable<T> {

    int size();

    boolean isEmpty();

    boolean add(T item);

    boolean remove(T item);

    void addFirst(T item);

    T getFirst();

    T getLast();
}
