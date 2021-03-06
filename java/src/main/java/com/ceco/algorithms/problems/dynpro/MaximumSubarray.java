package com.ceco.algorithms.problems.dynpro;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/maxsubarray">
 *          The Maximum Subarray
 *     </a>
 * <p>
 * Example input:
 * 2
 * 4
 * 1 2 3 4
 * 6
 * 2 -1 2 3 4 -5
 * <p>
 * Output:
 * 10 10
 * 10 11
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 19-Oct-2016
 */
public class MaximumSubarray {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < testCases; i++) {
            int arrSize = scanner.nextInt();
            int[] arr = new int[arrSize];
            for (int j = 0; j < arrSize; j++) {
                arr[j] = scanner.nextInt();
            }
            System.out.println(maxConsecutiveSubarray(arr) + " " + maxNonConsecutiveSubarray(arr));
        }
    }

    private static int maxConsecutiveSubarray(int[] arr) {
        int maxEnd = arr[0];
        int maxSoFar = arr[0];
        for (int i = 1; i < arr.length; i++) {
            // track current element and the sum of previous elements
            // if the current element is bigger then the sum, drop previous subarray
            // and start tracking a new one from that current max element and memorise
            // current sum in maxSoFar
            maxEnd = Math.max(arr[i], maxEnd + arr[i]);
            maxSoFar = Math.max(maxEnd, maxSoFar);
        }
        return maxSoFar;
    }

    private static int maxNonConsecutiveSubarray(int[] arr) {
        // order does not matter so sort ascendingly
        Arrays.sort(arr);
        int sum = 0;
        // if the last element is negative promote it as the max sum
        int lastElem = arr[arr.length - 1];
        if (lastElem <= 0) {
            sum = lastElem;
        } else {
            // if the array does not have a negative max element simply
            // sum all positive elements to get the max possible sum
            for (int num : arr) {
                if (num > 0) {
                    sum += num;
                }
            }
        }
        return sum;
    }
}
