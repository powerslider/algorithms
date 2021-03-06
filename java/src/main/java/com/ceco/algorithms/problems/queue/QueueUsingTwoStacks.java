package com.ceco.algorithms.problems.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/queue-using-two-stacks">
 *          Queue using two stacks
 *      </a>
 *
 * Example input:
 * 10
 * 1 42
 * 2
 * 1 14
 * 3
 * 1 28
 * 3
 * 1 60
 * 1 78
 * 2
 * 2
 *
 * Output:
 * 14
 * 14
 *
 * Example input:
 * 10
 * 1 76
 * 1 33
 * 2
 * 1 23
 * 1 97
 * 1 21
 * 3
 * 3
 * 1 74
 * 3
 *
 * Output:
 * 33
 * 33
 * 33
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 24-Sep-2016
 */
public class QueueUsingTwoStacks {

    private static class StackQueue<T> {

        Deque<T> frontStack = new ArrayDeque<>();
        Deque<T> backStack = new ArrayDeque<>();
        T head;

        T dequeue() {
            if (backStack.isEmpty()) {
                while (!frontStack.isEmpty()) {
                    backStack.push(frontStack.pop());
                }
            }
            T popped = backStack.pop();
            head = !backStack.isEmpty() ? backStack.peek() : null;

            return popped;
        }

        void enqueue(T queryInput) {
            frontStack.push(queryInput);
            if (frontStack.size() == 1 && backStack.isEmpty()) {
                head = frontStack.peek();
            }
        }

        T peek() {
            return head;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queriesCount = scanner.nextInt();
        scanner.nextLine();

        StackQueue<Integer> stackQueue = new StackQueue<>();

        for (int i = 0; i < queriesCount; i++) {
            String[] query = scanner.nextLine().split("\\s");
            int queryType = Integer.parseInt(query[0]);
            int queryInput = Integer.MIN_VALUE;
            if (query.length > 1) {
                queryInput = Integer.parseInt(query[1]);
            }
            switch (queryType) {
                case 1:
                    stackQueue.enqueue(queryInput);
                    break;
                case 2:
                    stackQueue.dequeue();
                    break;
                case 3:
                    System.out.println(stackQueue.peek());
                    break;
            }
        }
    }
}
