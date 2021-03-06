package com.ceco.algorithms.sorting;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 03 March 2015
 */
public abstract class AbstractSort<T> implements Sortable<T> {

    @Override
    public boolean less(T first, T second) {
        if (first instanceof Comparable) {
            return ((Comparable) first).compareTo(second) < 0;
        }
        throw new IllegalArgumentException("Cannot compare types that are not comparable!");
    }

    @Override
    public boolean isSorted(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (less(arr[i], arr[i-1])) return false;
        }
        return true;
    }

    @Override
    public void swap(T[] arr, int i, int j) {
        T item = arr[i];
        arr[i] = arr[j];
        arr[j] = item;
    }

    public void show(T[] arr) {
        for (T elem : arr) {
            System.out.println(elem);
        }
    }

    public abstract void sort(T[] arr);
}
