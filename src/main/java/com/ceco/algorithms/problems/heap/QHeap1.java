package com.ceco.algorithms.problems.heap;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/qheap1">
 *          QHEAP1
 *      </a>
 * <p>
 * Example input:
 * 5
 * 1 4
 * 1 9
 * 3
 * 2 4
 * 3
 * <p>
 * Output:
 * 4
 * 9
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 17-Oct-2016
 */
public class QHeap1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queriesCount = scanner.nextInt();
        scanner.nextLine();

        Queue<Integer> minHeap = new PriorityQueue<>(
                (a, b) -> a < b ? -1 : Objects.equals(a, b) ? 0 : 1);

        for (int i = 0; i < queriesCount; i++) {
            String[] queryParts = scanner.nextLine().split("\\s");
            String queryType = queryParts[0];
            int queryInput = Integer.MIN_VALUE;
            if (queryParts.length > 1) {
                queryInput = Integer.parseInt(queryParts[1]);
            }

            switch (queryType) {
                case "1":
                    minHeap.offer(queryInput);
                    break;
                case "2":
                    minHeap.remove(queryInput);
                    break;
                case "3":
                    System.out.println(minHeap.peek());
                    break;
            }
        }
    }
}
