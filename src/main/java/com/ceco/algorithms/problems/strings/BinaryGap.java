package com.ceco.algorithms.problems.strings;

import java.util.Scanner;

/**
 * @see <a href="https://codility.com/programmers/lessons/1-iterations/binary_gap/">
 *          Binary Gap
 *     </a>
 * <p>
 * Example input:
 * 9
 * <p>
 * Output:
 * 2
 * <p>
 * Example input:
 * 529
 * <p>
 * Output:
 * 4
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 02-Nov-2016
 */
public class BinaryGap {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        String binaryStr = Integer.toBinaryString(num);

        int binaryGapCount = 0;
        int max = 0;
        for (int i = 0; i < binaryStr.length(); i++) {
            char digit = binaryStr.charAt(i);
            if (digit == '0') {
                binaryGapCount++;
            } else {
                if (max < binaryGapCount) {
                    max = binaryGapCount;
                }
                binaryGapCount = 0;
            }
        }

        System.out.println(max);
    }
}
