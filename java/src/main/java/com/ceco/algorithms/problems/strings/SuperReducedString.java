package com.ceco.algorithms.problems.strings;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/reduced-string">
 *          Super Reduced String
 *     </a>
 *
 * Example input:
 * aaabccddd
 *
 * Output:
 * abd
 *
 * Example input:
 * baab
 *
 * Output:
 * Empty String
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 28-Sep-2016
 */
public class SuperReducedString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder str = new StringBuilder(scanner.nextLine());
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                str.delete(i, i + 2);
                if (str.length() == 0) {
                    System.out.println("Empty String");
                    break;
                }
                // If we have matched and deleted the first two characters
                // we have to set i = -1 because the next iteration will increment
                // it to 0 and can apply the check again on the next pair at
                // positions 0 and 1.
                //
                // If we have matched somewhere after the beginning just subtract two positions
                // and re-check again on the next iteration the next possible pair.
                i = (i == 0) ? -1 : i - 2;
            }
        }

        System.out.println(str);
    }
}
