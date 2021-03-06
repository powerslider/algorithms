package com.ceco.algorithms.problems.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @see <a href="">
 *          Equi
 *     </a>
 * <p>
 * Example input:
 * 8
 * -1 3 -4 5 1 -6 2 1
 * <p>
 * Output:
 * 1
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 06-Nov-2016
 */
public class EquilibriumIndex {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrSize = scanner.nextInt();
        scanner.nextLine();

        int[] arr = new int[arrSize];

        System.out.println(equi(arr));
    }

    private static int equi(int[] arr) {
        if (arr.length == 0) return -1;

        // sum all elements and memorize all partial sums
        // in a list in order to have them calculated when
        // comparing split sums
        int sum = 0;
        List<Integer> sumsList = new ArrayList<>();
        for (int elem : arr) {
            sum += elem;
            sumsList.add(sum);
        }

        for (int i = 0; i < arr.length - 1; i++) {
            // get partial sum of first halve directly from the list
            int firstHalfSum = sumsList.get(i);
            // calculate second half by substracting the first half from the
            // total and also the P element which you split upon
            int secondHalfSum = sum - firstHalfSum - arr[i + 1];
            // compare both split sums and return equilibrium index ;)
            if (firstHalfSum == secondHalfSum) {
                return i + 1;
            }
        }

        return -1;
    }
}
