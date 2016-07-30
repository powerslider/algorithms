package com.ceco.algorithms.sorting;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 03 March 2015
 */
public class SelectionSort<T> extends AbstractSort<T>{

    @Override
    public void sort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (less(arr[j], arr[i])) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        AbstractSort<Integer> selectionSort = new SelectionSort<>();
        selectionSort.sort(arr);
        selectionSort.show(arr);
    }
}
