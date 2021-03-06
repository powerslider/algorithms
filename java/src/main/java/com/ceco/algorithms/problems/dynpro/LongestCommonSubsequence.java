package com.ceco.algorithms.problems.dynpro;

import java.util.Scanner;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 27-Oct-2016
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arr1Size = scanner.nextInt();
        int arr2Size = scanner.nextInt();

        int[] arr1 = new int[arr1Size];
        for (int i = 0; i < arr1Size; i++) {
            arr1[i] = scanner.nextInt();
        }

        int[] arr2 = new int[arr2Size];
        for (int i = 0; i < arr2Size; i++) {
            arr2[i] = scanner.nextInt();
        }

        int[][] mat = new int[arr1Size + 1][arr2Size + 1];

        int[] lcs = longestCommonSubsequence(arr1Size - 1, arr2Size - 1, arr1, arr2, mat);
        for (int i : lcs) {
            System.out.printf(i + " ");
        }
    }

    private static int[] longestCommonSubsequence(int down, int right, int[] arr1, int[] arr2, int[][] mat) {
        int lcsLength = longestCommonSubsequenceLength(mat, arr1, arr2);
        int[] lcs = new int[lcsLength];
        int count = 0;
        while (down >= 1 && right >= 1) {
            if (arr1[down - 1] == arr2[right - 1]) {
                down--;
                right--;
                lcs[count] = mat[down][right];
                count++;
            } else {
                if (mat[down - 1][right] > mat[down][right - 1]) {
                    down--;
                } else {
                    right--;
                }
            }
        }

        return lcs;
    }

    private static int longestCommonSubsequenceLength(int[][] mat, int[] arr1, int[] arr2) {
        int max = 0;
        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat[i].length; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    mat[i][j] = mat[i - 1][j - 1] + 1;
                } else {
                    mat[i][j] = Math.max(mat[i][j - 1], mat[i - 1][j]);
                }

                if (max < mat[i][j]) {
                    max = mat[i][j];
                }
            }
        }

        return max;
    }
}
