package com.ceco.algorithms.problems.dynpro;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Explanation:
 * @see <a href="https://en.wikipedia.org/wiki/Partition_problem">
 *          Partition problem
 *     </a>
 * <p>
 * @see <a href="https://www.hackerrank.com/challenges/array-splitting">
 *          Nikita and the game
 *     </a>
 *
 * Example input:
 * 3
 * 3
 * 3 3 3
 * 4
 * 2 2 2 2
 * 7
 * 4 1 0 1 1 0 1
 * <p>
 * Output:
 * 0
 * 2
 * 3
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 19-Oct-2016
 */
public class NikitaAndTheGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < testCases; i++) {
            int arrSize = scanner.nextInt();
            scanner.nextLine();
            long[] arr = new long[arrSize];
            for (int j = 0; j < arrSize; j++) {
                arr[j] = scanner.nextLong();
            }

            System.out.println(partition(arr));
        }
    }

    private static int partition(long[] arr) {
        Set<Long> sumsSet = new HashSet<>();
        long sum = 0;
        boolean isAllZeros = true;
        // build a set of partial sums
        // and get the sum of all elements
        for (long num : arr) {
            sum += num;
            sumsSet.add(sum);
            if (num != 0) {
                // if the array contains only zeros there are n - 1 possible
                // partitionings due to sum of zeros leading again to a zero
                // and triggering a partition action
                isAllZeros = false;
            }
        }

        if (isAllZeros) {
            return arr.length - 1;
        } else {
            return findMaxDeepness(sumsSet, 0, sum);
        }
    }

    /**
     * Determines if there is a subset of sumsSet that sums to ⌊ sum / 2 ⌋:
     * If there is a subset, then:
     * <ul>
     *     <li>if sum is even, the rest of sumsSet also sums to ⌊ sum / 2 ⌋</li>
     *     <li>if sum is odd, then the rest of sumsSet sums to ⌈ sum / 2 ⌉. This is as good a solution as possible.</li>
     * </ul>
     * @param sumsSet
     *          set of all partial sums
     * @param min
     *          min sum of subarray
     * @param max
     *          max sum of subarray
     * @return number of partition actions possible
     */
    private static int findMaxDeepness(Set<Long> sumsSet, long min, long max) {
        long halvedSum = (min + max) / 2;
        boolean isPossibleSumEven = (min + max) % 2 == 0;
        if (isPossibleSumEven && sumsSet.contains(halvedSum)) {
            return 1 + Math.max(findMaxDeepness(sumsSet, min, halvedSum), findMaxDeepness(sumsSet, halvedSum, max));
        } else {
            return 0;
        }
    }
}
