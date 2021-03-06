package com.ceco.algorithms.sorting;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 03 March 2015
 */
public class InsertionSort<T> extends AbstractSort<T> {

    @Override
    public void sort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && less(arr[j], arr[j - 1]); j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        AbstractSort<Integer> insertionSort = new InsertionSort<>();
        insertionSort.sort(arr);
        insertionSort.show(arr);
    }
}
