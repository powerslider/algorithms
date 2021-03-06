package com.ceco.algorithms.combinatorics;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 06-Jan-2017
 */
public class Permutation {

    static void permute(int start, int[] arr) {
        if (start >= arr.length) {
            for (int e : arr) {
                System.out.print(e + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i);
            System.out.println(String.format("swap(%s, %s)", start, i));
            permute(start + 1, arr);
            swap(arr, start, i);
            System.out.println(String.format("  swap(%s, %s)", start, i));
        }
    }

    private static void swap(int[] arr, int start, int i) {
        int temp = arr[start];
        arr[start] = arr[i];
        arr[i] = temp;
    }

    static void strPermute(String s, String prefix) {
        if (s.length() == 0)
            System.out.println(prefix);

        for (int i = 0; i < s.length(); i++) {
            String rem = s.substring(0, i) + s.substring(i + 1);
            strPermute(rem, prefix + s.charAt(i));
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        permute(0, arr);

        strPermute("ABC", "");
    }
}
