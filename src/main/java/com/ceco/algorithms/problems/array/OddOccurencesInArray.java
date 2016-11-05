package com.ceco.algorithms.problems.array;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @see <a href="https://codility.com/programmers/lessons/2-arrays/odd_occurrences_in_array/">
 *        OddOccurencesInArray
 *     </a>
 * <p>
 * Example input:
 * 7
 * 9 3 9 3 9 7 9
 * <p>
 * Output:
 * 7
 * <p>
 * Example input:
 * 5
 * 2 2 3 3 4
 * <p>
 * Output:
 * 4
 * <p>
 * Example input:
 * 1
 * 42
 * <p>
 * Output:
 * 42
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 02-Nov-2016
 */
public class OddOccurencesInArray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrSize = scanner.nextInt();
        scanner.nextLine();

        int[] arr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(findUnpaired(arr));
    }

    private static int findUnpaired(int[] arr) {
        Arrays.sort(arr);
        int len = arr.length;
        if (len == 1) return arr[0];

        // if the length is odd do not iterate through the last element
        int effLen = len % 2 == 0 ? len : len - 1;

        for (int i = 0; i < effLen; i += 2) {
            if (arr[i] != arr[i + 1]) {
                return arr[i];
            }
        }
        // if the last element is the unpaired one the check below would catch it if it ever reaches this code
        return arr[len - 1] != arr[len - 2] ? arr[len - 1] : arr[0];
    }
}
