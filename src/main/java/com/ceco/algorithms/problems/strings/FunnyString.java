package com.ceco.algorithms.problems.strings;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/funny-string">
 *          Funny String
 *     </a>
 * <p>
 * Example input:
 * 2
 * acxz
 * bcxz
 * <p>
 * Output:
 * Funny
 * Not Funny
 * <p>
 * Example input:
 * 2
 * ivvkxq
 * ivvkx
 * <p>
 * Output:
 * Not Funny
 * NotFunny
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 02-Oct-2016
 */
public class FunnyString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int strCount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < strCount; i++) {
            char[] str = scanner.nextLine().toCharArray();
            int strLen = str.length;
            char[] revStr = new char[strLen];
            for (int j = 0; j < strLen; j++) {
                revStr[j] = str[strLen - j - 1];
            }

            if (isFunnyString(str, revStr)) {
                System.out.println("Funny");
            } else {
                System.out.println("Not Funny");
            }
        }
    }

    private static boolean isFunnyString(char[] str, char[] revStr) {
        for (int j = 1; j < str.length; j++) {
            boolean isFunnyString = Math.abs(str[j] - str[j - 1]) == Math.abs(revStr[j] - revStr[j - 1]);
            if (!isFunnyString) {
                return false;
            }
        }
        return true;
    }
}
