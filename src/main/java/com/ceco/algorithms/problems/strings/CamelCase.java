package com.ceco.algorithms.problems.strings;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/camelcase">
 *          CamelCase
 *     </a>
 *
 * Example input:
 * saveChangesInTheEditor
 *
 * Output:
 * 5
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 28-Sep-2016
 */
public class CamelCase {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder str = new StringBuilder(scanner.nextLine());
        int wordsCount = 1;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                wordsCount++;
            }
        }
        System.out.println(wordsCount);
    }
}
