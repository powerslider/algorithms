package com.ceco.algorithms.problems.array;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/circular-array-rotation">
*           Circular array rotation
 *     </a>
 *
 * Example input:
 * 3 2 3
 * 1 2 3
 * 0
 * 1
 * 2
 *
 * Output:
 * 2
 * 3
 * 1
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 28-Sep-2016
 */
public class CircularArrayRotation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrSize = scanner.nextInt();
        int rotationsCount = scanner.nextInt();
        int indexesCount = scanner.nextInt();
        scanner.nextLine();

        int[] arr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = scanner.nextInt();
        }

        int[] indexes = new int[indexesCount];
        for (int i = 0; i < indexesCount; i++) {
            indexes[i] = scanner.nextInt();
        }

        for (int i = 0; i < rotationsCount; i++) {
            int current = arr[arrSize - 1];
//            for (int j = arrSize - 2; j >= 0; j--) {
//                arr[j + 1] = arr[j];
//            }
            System.arraycopy(arr, 0, arr, 1, arrSize - 2 + 1);
            arr[0] = current;
        }

        for (int i = 0; i < indexesCount; i++) {
            System.out.println(arr[indexes[i]]);
        }
    }
}
