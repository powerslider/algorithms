package com.ceco.algorithms.problems.array;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/arrays-ds">
 *          Arrays - DS
 *     </a>
 *
 * Example input:
 * 4
 * 1 4 3 2
 *
 * Output:
 * 2 3 4 1
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 30-Jul-2016
 */
public class ReversedArray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        scanner.nextLine();
        int[] arr = new int[count];
        int[] revArr = new int[count];

        String arrInput = scanner.nextLine();
        String[] strArr = arrInput.split(" ");

        for (int i = 0; i < strArr.length; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            revArr[arr.length - 1 -i] = arr[i];
        }

        for (int elem : revArr) {
            System.out.print(elem + " ");
        }
    }
}
