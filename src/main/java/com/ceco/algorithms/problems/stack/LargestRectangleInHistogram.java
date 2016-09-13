package com.ceco.algorithms.problems.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * Assume for convenience that we have the heights of the N rectangles in an array.
 * We'll process the array from left to right, and maintain a stack of heights along with
 * an index noting how far to the left a rectangle of that height can extend.
 * The stack will contain the indices corresponding to rectangles where we can start creating a
 * rectangle at that index, extending as far to the right as we've seen without encountering a height
 * smaller than our given height. This means that the entries in the stack will, from bottom to top, be
 * monotonically increasing in height.
 *
 * When we're considering a rectangle of a given height, we look in the stack to find rectangles which would
 * not be able to extend past our given rectangle. If so, we compute the area of that rectangle and consider
 * it as a candidate, and then remove it from our stack.
 *
 * We'll keep removing elements from the stack until either the stack is empty or the top height on the stack is
 * not blocked by our current height. Then, we'll want to push our current height on the stack. Note that a rectangle
 * with height equal to our current height can extend further to the left if we removed rectangles from the stack. If we
 * haven't removed any rectangles from the stack, then the given index is the leftmost index that the given rectangle
 * can start.
 *
 * Once we've processed all the elements in the array, we'll need to clearthe stack to make sure that the maximum
 * rectangular area is not flush with the right side of the histogram.
 *
 * To note that this is linear time, we do amortized constant work per height
 * (we push at most a linear number of items on the stack).
 *
 * @see <a href="https://www.hackerrank.com/challenges/largest-rectangle">
 *          Largest Rectangle
 *     </a>
 *
 * Example input:
 * 5
 * 1 2 3 4 5
 *
 * Output:
 * 9
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 12-Sep-2016
 */
public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int barCount = scanner.nextInt();

        int[] barHeights = new int[barCount + 1];

        for (int i = 0; i < barHeights.length - 1; i++) {
            barHeights[i] = scanner.nextInt();
        }

        Stack<Integer> stack = new Stack<>();
        int maxArea = Integer.MIN_VALUE;
        int i;

        for (i = 0; i < barHeights.length;) {
            // The stack will contain the indices corresponding to rectangles where we can start creating
            // a rectangle at that index, extending as far to the right as we've seen without encountering a
            // height smaller than our given height. This means that the entries in the stack will, from bottom
            // to top, be monotonically increasing in height.
            if (stack.isEmpty() || barHeights[stack.peek()] <= barHeights[i]) {
                stack.push(i++);
            } else {
                maxArea = getMaxArea(barHeights, stack, maxArea, i);
            }
        }

        while (!stack.isEmpty()) {
            maxArea = getMaxArea(barHeights, stack, maxArea, i);
        }

        System.out.println(maxArea);
    }

    private static int getMaxArea(int[] barHeights, Stack<Integer> stack, int maxArea, int i) {
        int area;
        int top = stack.pop();

        if (stack.isEmpty()) {
            area = barHeights[top] * i;
        } else {
            area = barHeights[top] * (i - 1 - stack.peek());
        }

        if (maxArea < area) {
            maxArea = area;
        }

        return maxArea;
    }
}
