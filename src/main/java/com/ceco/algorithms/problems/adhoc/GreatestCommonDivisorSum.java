package com.ceco.algorithms.problems.adhoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerearth.com/amazon-hiring-challenge/algorithm/akash-and-gcd-1-15/">
 *          Akash and GCD 1
 *     </a>
 *
 * Example input:
 * 3
 * 3 4 3
 * 6
 * C 1 2
 * C 1 3
 * C 3 3
 * U 1 4
 * C 1 3
 * C 1 2
 *
 * Output:
 * 13
 * 18
 * 5
 * 21
 * 16
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 27-Sep-2016
 */
public class GreatestCommonDivisorSum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrSize = scanner.nextInt();
        scanner.nextLine();

        int[] arr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = scanner.nextInt();
        }

        int queriesCount = scanner.nextInt();
        scanner.nextLine();

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < queriesCount; i++) {
            String[] queryParts = scanner.nextLine().split("\\s");
            String queryType = queryParts[0];
            int firstArg = Integer.parseInt(queryParts[1]);
            int secondArg = Integer.parseInt(queryParts[2]);
            switch (queryType) {
                case "U":
                    arr[firstArg - 1] = secondArg;
                    break;
                case "C":
                    int answer = gcdSumForRange(arr, firstArg, secondArg);
                    result.add(answer);
                    break;
            }
        }

        result.forEach(System.out::println);
    }

    private static int gcdSumForRange(int[] arr, int lowerBoundIdx, int upperBoundIdx) {
        int sum = 0;
        while (lowerBoundIdx <= upperBoundIdx) {
            sum += gcdSum(arr, lowerBoundIdx);
            lowerBoundIdx++;
        }
        return sum % (10^9 + 7);
    }

    private static int gcdSum(int[] arr, int arg) {
        int upper = arr[arg - 1];
        int sum = 0;
        for (int j = 1; j <= upper ; j++) {
            sum += gcd(j, upper);
        }
        return sum;
    }

    private static int gcd(int a, int b) {
        return a == 0 || b == 0 ? Math.abs(a + b) : gcd(b, a % b);
    }
}
