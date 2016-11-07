package com.ceco.algorithms.problems.array;

import java.util.Scanner;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 06-Nov-2016
 */
public class SpiralMessage {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();

        char[][] mat = new char[rows][cols];
        for (int row = 0; row < rows; row++) {
            String line = scanner.nextLine();
            for (int col = 0; col < cols; col++) {
                mat[row][col] = line.charAt(col);
            }
        }

        System.out.println(decodeMessage(mat));
    }

    private static int decodeMessage(char[][] mat) {
        int wordCount = 0;

        int startRow = 0;
        int endRow = mat.length - 1;
        int startCol = 0;
        int endCol = mat[endRow].length - 1;

        while (startRow <= endRow && startCol <= endCol) {
            // bottom -> top
            for (int row = endRow; row >= startRow; row--) {
                System.out.print(mat[row][startCol]);
                if (mat[row][startCol] == '#' && mat[row + 1][startCol] != '#') {
                    wordCount++;
                }
            }
            startCol++;
            System.out.println();

            // left -> right
            for (int col = startCol; col <= endCol; col++) {
                System.out.print(mat[startRow][col]);
                if (mat[startRow][col] == '#' && mat[startRow][col - 1] != '#') {
                    wordCount++;
                }
            }
            startRow++;
            System.out.println();

            // top -> bottom
            for (int row = startRow; row <= endRow; row++) {
                System.out.print(mat[row][endCol]);
                if (mat[row][endCol] == '#' && mat[row - 1][endCol] != '#') {
                    wordCount++;
                }
            }
            endCol--;
            System.out.println();

            // right -> left
            for (int col = endCol; col >= startCol; col--) {
                System.out.print(mat[endRow][col]);
                if (mat[endRow][col] == '#' && mat[endRow][col + 1] != '#') {
                    wordCount++;
                }
            }
            System.out.println();
            endRow--;
        }
//        xaa##ar#rswx#aa
//        xaa##ar#rswxx
//        xaa##ar#rswxx#aaa#
        return wordCount;

    }
}
