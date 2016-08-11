package com.ceco.algorithms.problems.stack;

import java.util.Scanner;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov@ontotext.com>
 * @since 11-Aug-2016
 */
public class EqualStacks {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int n3 = scanner.nextInt();

        int smallestLength = smallest(n1, n2, n3);

        int[] arr1 = fillAndSumArray(scanner, n1);
        int[] arr2 = fillAndSumArray(scanner, n2);
        int[] arr3 = fillAndSumArray(scanner, n3);

    }

    private static int[] fillAndSumArray(Scanner scanner, int len) {
        int[] arr = new int[len];
        int[] revArr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = scanner.nextInt();
        }

        revArr[0] = arr[0];
        arr[1] += arr[0];
        for (int i = 1; i < len; i++) {
            revArr[i] = arr[i - 1] + arr[i];
        }

        return revArr;
    }

    private static int smallest(int n1, int n2, int n3) {
        int temp = Math.min(
                n1, n2);
        return Math.min(n3, temp);
    }
}
