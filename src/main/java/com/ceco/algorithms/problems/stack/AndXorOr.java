package com.ceco.algorithms.problems.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * @see <a href="https://www.hackerrank.com/challenges/and-xor-or">
 *          AND xor OR
 *     </a>
 *
 * Example input:
 * 5
 * 9 8 3 5 7
 *
 * Output:
 * 11
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 22-Aug-2016
 */
public class AndXorOr {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrLen = scanner.nextInt();
        scanner.nextLine();

        int[] arr = new int[arrLen];
        for (int i = 0; i < arrLen; i++) {
            arr[i] = scanner.nextInt();
        }

        Stack<Integer> stack = new Stack<>();
        int maxResult = Integer.MIN_VALUE;
        for (int n1 : arr) {
            while (!stack.isEmpty()) {
                int n2 = stack.peek();
                int result = (n1 & n2) ^ (n1 | n2) & (n1 ^ n2);
                if (maxResult < result) {
                    maxResult = result;
                }

                if (n1 < n2) {
                    stack.pop();
                } else {
                    break;
                }
            }
            stack.push(n1);
        }
        System.out.println(maxResult);
    }
}
