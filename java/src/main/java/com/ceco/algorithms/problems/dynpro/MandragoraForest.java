package com.ceco.algorithms.problems.dynpro;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/mandragora">
 *          Mandragora Forest
 *     </a>
 * <p>
 * Example input:
 * 1
 * 3
 * 3 2 2
 * <p>
 * Output:
 * 10
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 19-Oct-2016
 */
public class MandragoraForest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        for (int i = 0; i < testCases; i++) {
            int mandragorasCount = scanner.nextInt();
            scanner.nextLine();
            long[] mandragorasHealth = new long[mandragorasCount];
            long sum = 0;
            for (int j = 0; j < mandragorasCount; j++) {
                mandragorasHealth[j] = scanner.nextLong();
                // sum healths to simulate roughly experience points
                sum += mandragorasHealth[j];
            }
            System.out.println(calculateMaxExperience(mandragorasHealth, sum));
        }
    }

    private static long calculateMaxExperience(long[] mandragoraHealths, long sum) {
        // sort mandragora healths ascendingly
        Arrays.sort(mandragoraHealths);

        int mandragorasEaten = 1;
        long experience = sum;
        long result;

        //eat one by one and calculate exp from the fight with the rest
        for (long mandragorasHealth : mandragoraHealths) {
            mandragorasEaten++;
            // subtract health of an eaten mandragora which will not count as experience
            sum -= mandragorasHealth;
            //calculate maximum experience with the rest
            result = sum * mandragorasEaten;
            if (result > experience) {
                experience = result;
            }
        }
        return experience;
    }
}
