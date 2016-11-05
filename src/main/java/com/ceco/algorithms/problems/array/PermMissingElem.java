package com.ceco.algorithms.problems.array;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @see <a href="https://codility.com/programmers/lessons/3-time_complexity/perm_missing_elem/">
 *          PermMissingElem
 *     </a>
 * <p>
 * Example input:
 * 4
 * 2 1 3 5
 * <p>
 * Output:
 * 4
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 05-Nov-2016
 */
public class PermMissingElem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrSize = scanner.nextInt();

        int[] arr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = scanner.nextInt();
        }

        int generatedSum = IntStream.range(1, arr.length + 2).sum();
        int availableSum = IntStream.of(arr).sum();

        System.out.println(generatedSum - availableSum);
    }
}
