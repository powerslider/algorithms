package com.ceco.algorithms.problems.heap;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/find-the-running-median">
 *          Find the running median
 *     </a>
 *<p>
 * Explanation:
 * <p>
 * Two heaps: one min-heap and one max-heap. Each heap contains about one half of the data. Every element in
 * the min-heap is greater or equal to the median, and every element in the max-heap is less or equal to the median.
 * When the min-heap contains one more element than the max-heap, the median is in the top of the min-heap. And when the
 * max-heap contains one more element than the min-heap, the median is in the top of the max-heap.
 * When both heaps contain the same number of elements, the total number of elements is even. In this case you have to
 * choose according your definition of median: a) the mean of the two middle elements; b) the greater of the two;
 * c) the lesser; d) choose at random any of the two...
 * Every time you insert, compare the new element with those at the top of the heaps in order to decide where to insert
 * it. If the new element is greater than the current median, it goes to the min-heap. If it is less than the current
 * median, it goes to the max heap. Then you might need to rebalance. If the sizes of the heaps differ by more than one
 * element, extract the min/max from the heap with more elements and insert it into the other heap.
 * In order to construct the median heap for a list of elements, we should first use a linear time algorithm and find
 * the median. Once the median is known, we can simply add elements to the Min-heap and Max-heap based on the median value.
 * Balancing the heaps isn't required because the median will split the input list of elements into equal halves. If you
 * extract an element you might need to compensate the size change by moving one element from one heap to another. This way
 * you ensure that, at all times, both heaps have the same size or differ by just one element.
 * <p>
 * Example input:;
 * 6
 * 12
 * 4
 * 5
 * 3
 * 8
 * 7
 * <p>
 * Output:
 * 12.0
 * 8.0
 * 5.0
 * 4.5
 * 5.0
 * 6.0
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 12-Oct-2016
 */
public class FindTheRunningMedian {

    private static class MedianHeap {

        private final Queue<Integer> minHeap = new PriorityQueue<>(
                (a, b) -> a < b ? -1 : Objects.equals(a, b) ? 0 : 1);

        private final Queue<Integer> maxHeap = new PriorityQueue<>(
                (a, b) -> a < b ? 1 : Objects.equals(a, b) ? 0 : -1);

        void insert(int item) {
            if (isEmpty() || item <= median()) {
                maxHeap.add(item);
            } else {
                minHeap.add(item);
            }
            rebalance();
        }

        double median() {
            if (minHeap.size() == maxHeap.size()) {
                return (minHeap.peek() + maxHeap.peek()) / 2.0;
            } else if (minHeap.size() > maxHeap.size()) {
                return minHeap.peek();
            } else {
                return maxHeap.peek();
            }
        }

        boolean isEmpty() {
            return minHeap.isEmpty() && maxHeap.isEmpty();
        }

        private void rebalance() {
            int heapSizeDiff = Math.abs(minHeap.size() - maxHeap.size());
            if (heapSizeDiff > 1) {
                if (maxHeap.size() > minHeap.size()) {
                    minHeap.add(maxHeap.poll());
                } else {
                    maxHeap.add(minHeap.poll());
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCount = scanner.nextInt();
        scanner.nextLine();
        MedianHeap medianHeap = new MedianHeap();
        for (int i = 0; i < numCount; i++) {
            medianHeap.insert(scanner.nextInt());
            System.out.printf("%.1f\n", medianHeap.median());
        }
    }
}
