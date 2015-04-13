package com.ceco.algorithms.datastructure.list;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * <p/>
 * Date added: 2015-02-12
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
