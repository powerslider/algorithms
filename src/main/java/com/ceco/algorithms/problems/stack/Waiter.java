package com.ceco.algorithms.problems.stack;

import java.util.*;

/**
 * @see <a href="https://www.hackerrank.com/challenges/waiter">
 *          Waiter
 *     </a>
 *
 * Example input:
 * 5 1
 * 3 4 7 6 5
 *
 * Output:
 * 4
 * 6
 * 3
 * 7
 * 5
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 13-Sep-2016
 */
public class Waiter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int platesCount = scanner.nextInt();
        int platePiles = scanner.nextInt();

        int[] arr = new int[platesCount];

        for (int i = 0; i < platesCount; i++) {
            arr[i] = scanner.nextInt();
        }

        List<Integer> primes = sieveOfEratosthenes(platePiles + 1);

        Deque<Integer> nonDivisable = new ArrayDeque<>();

        for (int prime : primes) {
            if (nonDivisable.isEmpty()) {
                for (int num : arr) {
                    if (num % prime == 0) {
                        System.out.println(num);
                    } else {
                        nonDivisable.push(num);
                    }
                }
            } else {
                nonDivisable = splitNonDivisable(nonDivisable, prime);
            }
        }

        while (!nonDivisable.isEmpty()) {
            System.out.println(nonDivisable.pollLast());
        }
    }

    private static Deque<Integer> splitNonDivisable(Deque<Integer> nonDivisable, int prime) {
        Deque<Integer> otherNonDivisable = new ArrayDeque<>();
        while (!nonDivisable.isEmpty()) {
            int num = nonDivisable.pop();
            if (num % prime == 0) {
                System.out.println(num);
            } else {
                otherNonDivisable.push(num);
            }
        }

        return otherNonDivisable;
    }

    private static List<Integer> sieveOfEratosthenes(int primesCount) {
        List<Integer> primes = new ArrayList<>();
        boolean[] primesStatus = new boolean[primesCount + 1];
        Arrays.fill(primesStatus, true);

        for (int i = 2; i < Math.sqrt(primesCount); i++) {
            if (primesStatus[i]) {
                for (int j = i * i; j <= primesCount; j += i)
                    primesStatus[j] = false;
            }
        }

        for (int i = 2; i <= primesCount; i++) {
            if (primesStatus[i - 1]) {
                primes.add(i);
            }
        }

        return primes;
    }
}
