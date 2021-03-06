package com.ceco.algorithms.problems.strings;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/beautiful-binary-string">
 *          Beautiful Binary String
 *      </a>
 * <p>
 * Example input:
 * 7
 * 0101010
 * <p>
 * Output:
 * 2
 * <p>
 * Example input:
 * 5
 * 01100
 * <p>
 * Output:
 * 0
 * <p>
 * Example input:
 * 10
 * 0100101010
 * <p>
 * Output:
 * 3
 * <p>
 * Example input:
 * 100
 * 0100101010100010110100100110110100011100111110101001011001110111110000101011011111011001111100011101
 * <p>
 * Output:
 * 10
 * <p>
 * Example input:
 * 94
 * 0101000010011100111110011000001000100101100010000011010111111101110110001110111110110101001011
 * <p>
 * Output:
 * 9
 * <p>
 * Example input:
 * 100
 * 1110011110001100010100000011011101100001101010001111101101000010111111001110110000010110010011100010
 * <p>
 * Output:
 * 7
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 02-Oct-2016
 */
public class BeautifulBinaryString {

    private static final String UGLY_SUBSTRING = "010";
    private static final String PATCHED_SUBSTRING = "011";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int strLen = scanner.nextInt();
        scanner.nextLine();
        StringBuilder str = new StringBuilder(scanner.nextLine());

        int bitFlips = 0;
        String substring = str.substring(strLen - 3);
        if (substring.equals(UGLY_SUBSTRING)) {
            bitFlips++;
        }
        for (int i = 0; i < strLen - 3; i++) {
            String currentSubStr = str.substring(i, i + 3);
            if (currentSubStr.equals(UGLY_SUBSTRING)) {
                bitFlips++;
                str.replace(i, i + 3, PATCHED_SUBSTRING);
            }
        }

        System.out.println(bitFlips);
    }
}
