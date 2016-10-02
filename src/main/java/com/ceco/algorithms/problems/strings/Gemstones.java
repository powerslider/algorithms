package com.ceco.algorithms.problems.strings;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @see <a href="https://www.hackerrank.com/challenges/gem-stones">
 *          Gemstones
 *     </a>
 * <p>
 * Example input:
 * 3
 * abcdde
 * baccd
 * eeabg
 * <p>
 * Output:
 * 2
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 02-Oct-2016
 */
public class Gemstones {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rockCount = scanner.nextInt();
        scanner.nextLine();

        Set<Character> strSet = toSet(scanner.nextLine());
        for (int i = 0; i < rockCount - 1; i++) {
            Set<Character> nextStrSet = toSet(scanner.nextLine());
            strSet.retainAll(nextStrSet);
        }

        System.out.println(strSet.size());
    }

    private static Set<Character> toSet(String str) {
        Set<Character> strSet = new HashSet<>(26);
        for (char c : str.toCharArray()) {
            strSet.add(c);
        }
        return strSet;
    }
}
