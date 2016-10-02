package com.ceco.algorithms.problems.strings;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 02-Oct-2016
 */
public class Pangrams {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().toLowerCase().trim();
        if (str.length() == 1) {
            System.out.println("not pangram");
            System.exit(0);
        }

        Set<Character> characterSet = new TreeSet<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ') {
                characterSet.add(c);
            }
        }

        int count = 0;
        for (Character c : characterSet) {
            if (c != ALPHABET.charAt(count)) {
                System.out.println("not pangram");
                System.exit(0);
            }
            count++;
        }
        System.out.printf("pangram");

    }
}
