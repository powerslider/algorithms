package com.ceco.algorithms.problems.array;

import java.util.Objects;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/arrays-ds">
 *          Sparse Arrays
 *     </a>
 *
 * Example input:
 * 4
 * aba
 * baba
 * aba
 * xzxb
 * 3
 * aba
 * xzxb
 * ab
 *
 * Output:
 * 2
 * 1
 * 0
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 02-Aug-2016
 */
public class SparseArrays {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int wordCount = scanner.nextInt();
        scanner.nextLine();

        String[] words = new String[wordCount];

        for (int i = 0; i < wordCount; i++) {
            words[i] = scanner.nextLine();
        }

        int queriesCount = scanner.nextInt();
        scanner.nextLine();

        String[] queries = new String[queriesCount];

        for (int i = 0; i < queriesCount; i++) {
            queries[i] = scanner.nextLine();
        }

        int occurences = 0;
        for (int i = 0; i < queriesCount; i++) {
            for (int j = 0; j < wordCount; j++) {
                if (Objects.equals(queries[i], words[j])) {
                    occurences++;
                }
            }
            System.out.println(occurences);
            occurences = 0;
        }
    }
}
