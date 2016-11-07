package com.ceco.algorithms.problems.array;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/contests/ncr-codesprint/challenges/counting-mistakes">
 *          Counting Mistakes
 *     </a>
 * <p>
 * Example input:
 * 4
 * 3 4 7 7
 * <p>
 * Output:
 * 3
 * <p>
 * Example input:
 * 5
 * 1 3 2 3 4
 * <p>
 * Output:
 * 2
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 06-Nov-2016
 */
public class CountingMistakes {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrSize = scanner.nextInt();
        scanner.nextLine();

        int[] arr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(checkForMistakes(arr));

    }

    private static int checkForMistakes(int[] arr) {
        int mistakes = 0;
        if (arr[0] != 1) mistakes++;
        for (int i = arr.length - 1; i >= 1; i--) {
            if (arr[i] - 1 != arr[i - 1]) {
                mistakes++;
            }
        }

        return mistakes;
    }
}
