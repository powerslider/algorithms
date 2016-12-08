package com.ceco.algorithms.problems.strings;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/game-of-thrones">
 *          Game of Thrones I
 *     </a>
 * <p>
 * Example input:
 * aaabbbb
 * <p>
 * Output:
 * YES
 * <p>
 * Example input:
 * cdefghmnopqrstuvw
 * <p>
 * Output:
 * NO
 * <p>
 * Example input:
 * cdcdcdcdeeeef
 * <p>
 * Output:
 * YES
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 08-Dec-2016
 */
public class GameOfThronesI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (isPalindromePermutation(scanner.nextLine())) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean isPalindromePermutation(String s) {
        int[] table = buildCharFreqTable(s);
        return checkMaxOneOdd(table);
    }

    /**
     * Generate a char index for the table, e.g, a = 0, b = 1, c = 2, etc.
     */
    private static int generateCharIdx(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a';
        }
        return -1;
    }


    /**
     * Build a frequency table tracing the occurences of each character in the string.
     */
    private static int[] buildCharFreqTable(String s) {
        int[] table = new int['z' - 'a' + 1];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            int idx = generateCharIdx(c);
            if (idx != -1) {
                table[idx]++;
            }
        }
        return table;
    }


    /**
     * Check if there is at most one odd count. If it is only one or none it could be a
     * palindrome.
     */
    private static boolean checkMaxOneOdd(int[] table) {
        boolean foundOdd = false;
        for (int charCount : table) {
            if (charCount % 2 == 1) {
                if (foundOdd) {
                    return false;
                }
                foundOdd = true;
            }
        }
        return true;
    }
}
