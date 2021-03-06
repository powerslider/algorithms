package com.ceco.algorithms.problems.heap;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/jesse-and-cookies">
 *          Jesse and cookies
 *     </a>
 * <p>
 * Example input:
 * 6 7
 * 1 2 3 9 10 12
 * <p>
 * Output:
 * 2
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 17-Oct-2016
 */
public class JesseAndCookies {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cookiesCount = scanner.nextInt();
        int minSweetness = scanner.nextInt();
        scanner.nextLine();

        Queue<Integer> cookiesHeap = new PriorityQueue<>(
                (a, b) -> a < b ? -1 : Objects.equals(a, b) ? 0 : 1);

        for (int i = 0; i < cookiesCount; i++) {
            cookiesHeap.offer(scanner.nextInt());
        }

        int count = 0;
        try {
            while (!cookiesHeap.isEmpty() && cookiesHeap.peek() < minSweetness) {
                int newSweeterCookie = cookiesHeap.poll() + 2 * cookiesHeap.poll();
                cookiesHeap.offer(newSweeterCookie);
                count++;
            }
            System.out.println(count);
        } catch (Exception e) {
            System.out.println(-1);
        }
    }
}
