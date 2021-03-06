package com.ceco.algorithms.datastructure.list;


import java.util.Iterator;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 13 Feb 2015
 */
public class ArrayList<T> implements List<T> {

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    private T[] arr;

    private int capacity;

    private int last;

    public ArrayList() {
        this.capacity = DEFAULT_CAPACITY;
        this.arr = (T[]) new Object[capacity];
        this.last = -1;
    }

    public ArrayList(int initialCapacity) {
        this.capacity = initialCapacity;
        this.last = -1;
        if (capacity >= 0) {
            this.arr = (T[]) new Object[capacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    @Override
    public int size() {
        return this.arr.length;
    }

    @Override
    public boolean isEmpty() {
        return this.last == 0;
    }

    @Override
    public boolean add(T item) {
        if (last + 1 == capacity) {
            resize(capacity * 2);
        }
        arr[last++] = item;
        return true;
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

    private void resize(int capacity) {
        T[] newArr = (T[]) new Object[capacity];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        this.arr = newArr;
        this.capacity = arr.length;
    }
}
