package com.ceco.algorithms.problems.array;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/equal-stacks">
 *          Equal Stacks
 *     </a>
 *
 * Example input:
 * 5 3 4
 * 3 2 1 1 1
 * 4 3 2
 * 1 1 4 1
 *
 * Output:
 * 5
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov@ontotext.com>
 * @since 11-Aug-2016
 */
public class EqualStacks {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int n3 = scanner.nextInt();


        int[] arr1 = fillAndReverseSumArray(scanner, n1);
        int[] arr2 = fillAndReverseSumArray(scanner, n2);
        int[] arr3 = fillAndReverseSumArray(scanner, n3);

        int[][] sortedArrays = sortArraysOnLength(arr1, arr2, arr3);
        int[] smallestArr = sortedArrays[0];
        int oldFound = Integer.MIN_VALUE;
        int smallestArrLength = smallestArr.length == 1 ? smallestArr.length : smallestArr.length - 1;
        int maxHeight = 0;

        for (int i = smallestArrLength; i >= 1; i--) {
            for (int j = sortedArrays.length - 1; j >= 1; j--) {
                int[] currentArr = sortedArrays[j];
                int item = smallestArr.length == 1 ? smallestArr[0] : smallestArr[i];
                int foundIdx = binarySearch(currentArr, 0, currentArr.length, item);
                if (foundIdx > -1) {
                    if (oldFound == currentArr[foundIdx]) {
                        maxHeight = currentArr[foundIdx];
                        break;
                    }
                    oldFound = currentArr[foundIdx];
                }
            }
        }
        System.out.println(maxHeight);
    }

    private static int[] fillAndReverseSumArray(Scanner scanner, int len) {
        int[] arr = new int[len];
        int[] revArr = new int[len];
        if (len > 0) {
            for (int i = 0; i < len; i++) {
                arr[i] = scanner.nextInt();
            }

            revArr[0] = arr[len - 1];
            for (int i = len - 2; i >= 0; i--) {
                if (i == len - 2) {
                    revArr[len - 1 - i] = arr[i + 1] + arr[i];
                } else {
                    revArr[len - 1 - i] = revArr[len - 2 - i] + arr[i];
                }
            }
        }

        return revArr;
    }

    private static int[][] sortArraysOnLength(int[] arr1, int[] arr2, int[] arr3) {
        // kinda dumm but good exercise on jagged arrays ;)
        int[][] jaggedArr = new int[3][];
        int[] tempArr;
        if (arr1.length >= arr2.length) {
            tempArr = arr2;
            jaggedArr[0] = arr2;
            jaggedArr[1] = arr1;
        } else {
            tempArr = arr1;
            jaggedArr[0] = arr1;
            jaggedArr[1] = arr2;
        }

        if (tempArr.length > arr3.length) {
            jaggedArr[0] = arr3;
            jaggedArr[2] = tempArr;
        } else {
            jaggedArr[2] = arr3;
        }

        return jaggedArr;
    }

    private static int binarySearch(int[] arr, int lowerbound, int upperbound, int item) {
        if (upperbound == 1) {
            return arr[0] == item ? 0 : -1;
        }

        int position = (lowerbound + upperbound) / 2;
        // while we have not found the element and the lower bound does not surpass the upperbound
        while (position < upperbound && arr[position] != item && lowerbound <= upperbound) {
            // if the current element is less then the searched item
            if (arr[position] < item) {
                // discard elements below the lower bound, the lower bound itself
                // and update the new lower bound right after the current median
                lowerbound = position + 1;
            } else {
                // discard elements above the upper bound, the upper bound itself
                // and update the new upper bound right before the current median
                upperbound = position - 1;
            }

            position = (lowerbound + upperbound) / 2;
        }

        if (lowerbound <= upperbound && position < arr.length) return position;

        return -1;
    }
}
