package com.ceco.algorithms.sorting;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 *         <p/>
 *         Date added: 2015-03-03
 */
public interface Sortable<T> {

    void sort(T[] arr);

    boolean less(T first, T second);

    boolean isSorted(T[] arr);

    void swap(T[] arr, int i, int j);
}
