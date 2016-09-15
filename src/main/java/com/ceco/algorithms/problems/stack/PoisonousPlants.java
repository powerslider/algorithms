package com.ceco.algorithms.problems.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov@ontotext.com>
 * @since 15-Sep-2016
 */
public class PoisonousPlants {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int plantCount = scanner.nextInt();

        int[] pesticides = new int[plantCount];
        int[] killerPlantSpans = new int[plantCount];

        for (int i = 0; i < pesticides.length; i++) {
            pesticides[i] = scanner.nextInt();
        }

        Deque<Integer> stack = new ArrayDeque<>();
//        stack.push(0);

        killerPlantSpans[0] = 1;
        int daysToDie = 0;
        int maxDaysToDie = 0;
        for (int i = 1; i < plantCount; i++) {
            // while the element with the top stack index is less than the current element
            // pop the element from the stack
            while (!stack.isEmpty() && pesticides[stack.peek()] <= pesticides[i]) {
                daysToDie++;
                stack.pop();
            }

            killerPlantSpans[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek());

            if (daysToDie > maxDaysToDie) maxDaysToDie = daysToDie;

            stack.push(i);
        }

        for (int k : killerPlantSpans) {
            System.out.println(k);
        }

        System.out.println(maxDaysToDie);
    }

}
