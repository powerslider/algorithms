package com.ceco.algorithms.problems.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/alternating-characters">
 *          Alternating Characters
 *     </a>
 * <p>
 * Example input:
 * 5
 * AAAA
 * BBBBB
 * ABABABAB
 * BABABA
 * AAABBB
 * <p>
 * Output:
 * 3
 * 4
 * 0
 * 0
 * 4
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 02-Oct-2016
 */
public class AlternatingCharacters {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int strCount = scanner.nextInt();
        scanner.nextLine();

        List<Integer> result = new ArrayList<>(strCount);

        for (int i = 0; i < strCount; i++) {
            String str = scanner.nextLine();
            int deletionsCount = 0;
            for (int j = 0; j < str.length() - 1; j++) {
                if (str.charAt(j) == str.charAt(j + 1)) {
                    deletionsCount++;
                }
            }
            result.add(deletionsCount);
        }

        result.forEach(System.out::println);
    }
}
