package com.ceco.algorithms.problems.array;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/arrays-ds">
 *          Left Rotation
 *     </a>
 *
 * Example input:
 * 5 4
 * 1 2 3 4 5
 *
 * Output:
 * 5 1 2 3 4
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 01-Aug-2016
 */
public class LeftRotation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strArgs = scanner.nextLine().split("\\s");
        int numCount = Integer.parseInt(strArgs[0]);
        int numRotations = Integer.parseInt(strArgs[1]);
        String[] strArr = scanner.nextLine().split("\\s");
        int[] arr = new int[numCount];

        for (int i = 0; i < numCount; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
        }

        for (int i = 0; i < numRotations; i++) {
            // get first element of array
            int firstElem = arr[0];

            // shift every array element to its previous index
            for (int j = 1; j < arr.length; j++) {
                arr[j - 1] = arr[j];
            }
            // more optimized for Java but less readable
            //System.arraycopy(arr, 1, arr, 0, arr.length - 1);

            // assign first element to last position
            // first element will always be different
            // at every next iteration due to shifting
            // of the entire array
            arr[arr.length - 1] = firstElem;
        }

        for (int elem : arr) {
            System.out.print(elem + " ");
        }
    }
}
