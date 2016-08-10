package com.ceco.algorithms.problems.array;

import java.util.Scanner;

/**
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
            int currentElem = arr[0];
            for (int j = 1; j < arr.length; j++) {
                arr[j - 1] = arr[j];
            }
            arr[arr.length - 1] = currentElem;
        }

        for (int elem : arr) {
            System.out.print(elem + " ");
        }
    }
}
