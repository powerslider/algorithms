package com.ceco.algorithms.combinatorics;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 02-Feb-2017
 */
public class Combination {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        // for a sequence with length n, there are 2^n - 1 possible subsets
        // each number from 0 to 2^n - 1 represents a subset
        for (int i = 0; i < 1 << arr.length; i++) {
            int temp = i;
            for (int elem : arr) {
                // the 1 represents that this element is from the subset
                // 0 represents otherwise
                if ((temp & 1) == 1) {
                    System.out.print(elem);
                }
                temp >>= 1;
//                System.out.print("temp=" + Integer.toBinaryString(temp) + " |");
            }
            System.out.println();
        }

    }
}
