package com.ceco.algorithms.problems.array;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/contests/ncr-codesprint/challenges/spiral-message">
 *          Spiral Message
 *     </a>
 * <p>
 * Example input:
 * 3 5
 * a##ar
 * a#aa#
 * xxwsr
 * <p>
 * Output:
 * 4
 * <p>
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
        StringBuilder message = new StringBuilder();

        int startRow = 0;
        int endRow = mat.length - 1;
        int startCol = 0;
        int endCol = mat[endRow].length - 1;

        while (startRow <= endRow && startCol <= endCol) {
            if (startCol < endCol) {
                // bottom -> top
                for (int row = endRow; row >= startRow; row--) {
                    message.append(mat[row][startCol]);
                }
                startCol++;
            }

            // left -> right
            for (int col = startCol; col <= endCol; col++) {
                message.append(mat[startRow][col]);
            }
            startRow++;

            // top -> bottom
            for (int row = startRow; row <= endRow; row++) {
                message.append(mat[row][endCol]);
            }
            endCol--;

            if (startRow < endRow) {
                // right -> left
                for (int col = endCol; col >= startCol; col--) {
                    message.append(mat[endRow][col]);
                }
                endRow--;
            }
        }

        String msg = message.toString();
        if (msg.isEmpty()) return 0;

        return msg.split("\\#+").length;
    }
}
