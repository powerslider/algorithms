package com.ceco.algorithms.problems.strings;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/mars-exploration">
 *          Mars Exploration"
 *     </a>
 * <p>
 * Example input:
 * SOSOOSOSOSOSOSSOSOSOSOSOSOS
 * <p>
 * Output:
 * 12
 * <p>
 * Example input:
 * SOSSPSSQSSOR
 * <p>
 * Output:
 * 3
 * <p>
 * Example input:
 * SOSSOT
 * <p>
 * Output:
 * 1
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 02-Oct-2016
 */
public class MarsExploration {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder str = new StringBuilder(scanner.nextLine());
        int alteredMessages = 0;

        for (int i = 0; i < str.length(); i += 3) {
            char first = str.charAt(i);
            char second = str.charAt(i + 1);
            char third = str.charAt(i + 2);
            if (first != 'S')
                alteredMessages++;
            if (second != 'O')
                alteredMessages++;
            if (third != 'S')
                alteredMessages++;
        }
        System.out.println(alteredMessages);
    }
}
