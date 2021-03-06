package com.ceco.algorithms.problems.strings;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/contests/magic-lines-july-2015/challenges/run-length-encoding">
 *          Run-Length Encoding
 *     </a>
 * <p>
 * Example input:
 * AABBBCCCC
 * <p>
 * Output:
 * A2B3C4
 * <p>
 * Example input:
 * DFFFDDGGDFEEF
 * <p>
 * Output:
 * D1F3D2G2D1F1E2F1
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 06-Dec-2016
 */
public class RunLengthEncoding {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String encodedStr = encode(scanner.nextLine());
        System.out.println(encodedStr);
    }

    private static String encode(String str) {
        char[] chars = str.toCharArray();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            int runLength = 1;
            while (i + 1 < chars.length && chars[i] == chars[i + 1]) {
                runLength++;
                i++;
            }
            builder
                    .append(chars[i])
                    .append(runLength);
        }

        return builder.toString();
    }
}
