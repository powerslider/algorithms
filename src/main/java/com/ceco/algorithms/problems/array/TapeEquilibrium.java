package com.ceco.algorithms.problems.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @see <a href="https://codility.com/programmers/lessons/3-time_complexity/tape_equilibrium/">
 *          TapeEquilibrium
 *     </a>
 * <p>
 * Example input:
 * 5
 * 3 1 2 4 3
 * <p>
 * Output:
 * 1
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 05-Nov-2016
 */
public class TapeEquilibrium {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrSize = scanner.nextInt();
        scanner.nextLine();

        int[] arr = new int[arrSize];

        int sum = 0;
        List<Integer> sumsList = new ArrayList<>();
        for (int i = 0; i < arrSize; i++) {
            arr[i] = scanner.nextInt();
            sum += arr[i];
            sumsList.add(sum);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= arrSize; i++) {
            int firstHalfSum = sumsList.get(i - 1);
            int secondHalfSum = sum - firstHalfSum;
            int sumDifference = Math.abs(firstHalfSum - secondHalfSum);
            if (sumDifference < min) {
                min = sumDifference;
            }
        }

        System.out.println(min);
    }
}
