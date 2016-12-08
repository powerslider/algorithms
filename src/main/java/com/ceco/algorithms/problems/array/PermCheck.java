package com.ceco.algorithms.problems.array;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @see <a href="https://codility.com/programmers/lessons/4-counting_elements/perm_check/">
 *          PermCheck
 *     </a>
 * <p>
 * Example input:
 * 4
 * 4 1 3 2
 * <p>
 * Output:
 * 1
 * <p>
 * Example input:
 * 3
 * 4 1 3
 * <p>
 * Output:
 * 0
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 05-Nov-2016
 */
public class PermCheck {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrSize = scanner.nextInt();
        scanner.nextLine();

        int[] arr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(isPermutation(arr));
    }

    private static int isPermutation(int[] arr) {
        int generatedSum = IntStream.range(1, arr.length + 1).sum();
        int availableSum = IntStream.of(arr).sum();

        if (generatedSum == availableSum) {
            return 1;
        } else {
            return 0;
        }
    }
}
