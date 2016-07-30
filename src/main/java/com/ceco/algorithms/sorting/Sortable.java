package com.ceco.algorithms.sorting;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 03 March 2015
 */
public interface Sortable<T> {

    void sort(T[] arr);

    boolean less(T first, T second);

    boolean isSorted(T[] arr);

    void swap(T[] arr, int i, int j);
}
