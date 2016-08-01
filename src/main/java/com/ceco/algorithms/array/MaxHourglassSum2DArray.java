package com.ceco.algorithms.array;

import java.util.Scanner;

/**
 * Example input:
 * -1 -1 0 -9 -2 -2
 * -2 -1 -6 -8 -2 -5
 * -1 -1 -1 -2 -3 -4
 * -1 -9 -2 -4 -4 -5
 * -7 -3 -3 -2 -9 -9
 * -1 -3 -1 -2 -4 -5
 *
 * Output:
 * -6
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 30-Jul-2016
 */
public class MaxHourglassSum2DArray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] mat = new int[6][6];

        for (int row = 0; row < mat.length; row++) {
            String[] strArr = scanner.nextLine().split("\\s");
            for (int col = 0; col < mat[0].length - 1; col++) {
                mat[row][col] = Integer.parseInt(strArr[col]);
            }
        }

        int maxSum = Integer.MIN_VALUE;
        for (int row = 0; row < mat.length - 1; row++) {
            for (int col = 1; col < mat[0].length - 1; col++) {
                if (row + 2 <= mat.length - 1) {
                    int upperLeft = mat[row][col - 1];
                    int upperRight = mat[row][col + 1];
                    int upperMiddle = mat[row][col];
                    int middleMiddle = mat[row + 1][col];
                    int bottowLeft = mat[row + 2][col - 1];
                    int bottomRight= mat[row + 2][col + 1];
                    int bottomMiddle= mat[row + 2][col];

                    int sum = upperLeft + upperMiddle + upperRight
                                        + middleMiddle +
                              bottowLeft + bottomMiddle + bottomRight;
                    if (maxSum < sum) {
                        maxSum = sum;
                    }
                }
            }
        }
        System.out.println(maxSum);
    }
}
