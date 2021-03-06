package com.ceco.algorithms.problems.strings;

import java.util.Scanner;

/**
 * @see <a href="">
 *          Richie Rich
 *     </a>
 * <p>
 * Example input:
 * 4 1
 * 3943
 * <p>
 * Output:
 * 3993
 * <p>
 * Example input:
 * 6 3
 * 092282
 * <p>
 * Output:
 * 992299
 * <p>
 * Example input:
 * 4 1
 * 0011
 * <p>
 * Output:
 * -1
 * <p>
 * Example input:
 * 5 4
 * 11331
 * <p>
 * Output:
 * 99399
 * <p>
 * Example input:
 * 5 1
 * 12321
 * <p>
 * Output:
 * 12921
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 20-Nov-2016
 */
public class RichieRich {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int strLen = scanner.nextInt();
        int charsToModify = scanner.nextInt();
        scanner.nextLine();

        char[] str = scanner.nextLine().toCharArray();
        System.out.println(palidrominize(str, charsToModify));
    }

    private static char[] palidrominize(char[] chars, int charsToModify) {
        int len = chars.length;

        if (isPalindrome(chars) && len % 2 > 0) {
            chars[len / 2] = '9';
            charsToModify--;
        }

        for (int i = 0; i < len / 2 && charsToModify > 0; i++) {
            if (chars[i] != chars[len - 1 - i]) {
                charsToModify--;
                chars[i] = chars[len - 1 - i] = (char) Math.max(chars[i], chars[len - 1 - i]);
            }
        }

        for (int i = 0; i < len / 2 && charsToModify > 0; i++, charsToModify -= 2) {
            chars[i] = chars[len - 1 - i] = '9';
        }

        return !isPalindrome(chars) ? new char[]{'-', '1'} : chars;
    }

    private static boolean isPalindrome(char[] chars) {
        int len = chars.length;
        for (int i = 0; i < len / 2; i++) {
            if (chars[i] != chars[len - 1 - i]) {
                return false;
            }
        }
        return true;
    }
}

