package com.ceco.algorithms.problems.array;

import java.util.Scanner;
import java.util.stream.LongStream;

/**
 * @see <a href="https://www.hackerrank.com/contests/university-codesprint/challenges/mini-max-sum">
 *          Mini-Max Sum
 *     </a>
 * <p>
 * Example input:
 * 1 2 3 4 5
 * <p>
 * Output:
 * 10 14
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 11-Nov-2016
 */
public class MiniMaxSum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long[] arr = new long[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextLong();
        }

        long sum = LongStream.of(arr).sum();

        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;

        for (long elem : arr) {
            long currentSum = sum - elem;
            if (max < currentSum) {
                max = currentSum;
            }

            if (min > currentSum) {
                min = currentSum;
            }
        }

        System.out.println(min + " " + max);
    }
}
