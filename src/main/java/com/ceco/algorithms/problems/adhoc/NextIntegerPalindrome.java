package com.ceco.algorithms.problems.adhoc;

import java.util.Scanner;

/**
 * Example input:
 * 2
 * 808
 * 2133
 *
 * Output:
 * 818
 * 2222
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 07-Aug-2016
 */
public class NextIntegerPalindrome {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCount = scanner.nextInt();
        int[] arr = new int[numCount];

        for (int i = 0; i < numCount; i++) {
            arr[i] = scanner.nextInt();
        }

        for (int num : arr) {
            do {
                num++;
            } while (!isIntegerPalindrome(num));
            System.out.println(num);
        }
    }

    private static boolean isIntegerPalindrome(int num) {
        int oldNum = num;
        int rev = 0;
        while (num != 0) {
            rev = rev * 10 + num % 10;
            num /= 10;
        }
        return rev == oldNum;
    }
}
