package com.ceco.algorithms.problems.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 12-Sep-2016
 */
public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int barCount = scanner.nextInt();

        int[] barHeights = new int[barCount];

        for (int i = 0; i < barHeights.length; i++) {
            barHeights[i] = scanner.nextInt();
        }

        Stack<Integer> stack = new Stack<>();
    }
}
