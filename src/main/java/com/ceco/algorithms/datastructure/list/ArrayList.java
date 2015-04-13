package com.ceco.algorithms.datastructure.list;


import java.util.Iterator;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * <p/>
 * Date added: 2015-02-13
 */
public class ArrayList<T> implements List<T> {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean add(T item) {
        return false;
    }

    @Override
    public boolean remove(T item) {
        return false;
    }

    @Override
    public void addFirst(T item) {

    }

    @Override
    public T getFirst() {
        return null;
    }

    @Override
    public T getLast() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
