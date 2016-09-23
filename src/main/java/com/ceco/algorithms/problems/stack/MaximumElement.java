package com.ceco.algorithms.problems.stack;

import java.util.*;

/**
 * @see <a href="https://www.hackerrank.com/challenges/maximum-element">
 *          Maximum element
 *     </a>
 *
 * Example input:
 * 10
 * 1 97
 * 2
 * 1 20
 * 2
 * 1 26
 * 1 20
 * 2
 * 3
 * 1 91
 * 3
 *
 * Output:
 * 26
 * 91
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 23-Sep-2016
 */
public class MaximumElement {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queriesCount = scanner.nextInt();
        scanner.nextLine();

        Deque<Integer> maxStack = new ArrayDeque<>();
        List<Integer> maxElems = new ArrayList<>();

        for (int i = 0; i < queriesCount; i++) {
            String[] query = scanner.nextLine().split("\\s");
            int queryType = Integer.parseInt(query[0]);
            int queryInput = Integer.MIN_VALUE;
            if (query.length > 1) {
                queryInput = Integer.parseInt(query[1]);
            }

            switch (queryType) {
                case 1:
                    if (!maxStack.isEmpty()) {
                        // maintain a stack that is descendingly sorted
                        // if the top is less than the current input push it as the new top
                        // else replicate the current top as a new pushed entry to maintain
                        // same length on both stacks in order for the stack to keep the
                        // current maximum even after a pop query on it
                        if (maxStack.peek() < queryInput) {
                            maxStack.push(queryInput);
                        } else {
                            maxStack.push(maxStack.peek());
                        }
                    } else {
                        maxStack.push(queryInput);
                    }
                    break;
                case 2:
                    maxStack.pop();
                    break;
                case 3:
                    maxElems.add(maxStack.peek());
                    break;
            }
        }

        maxElems.forEach(System.out::println);
    }
}
