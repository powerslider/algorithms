package com.ceco.algorithms.problems.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

/**
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

        int[] primes = sieveOfEratosthenes(platePiles);

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

    private static int[] sieveOfEratosthenes(int primesCount) {
        int[] primes = new int[primesCount];
        boolean[] primesStatus = new boolean[primesCount + 1];
        Arrays.fill(primesStatus, true);
        if (primesCount == 1) {
            primes[0] = 2;
            return primes;
        } else if (primesCount == 2) {
            primes[0] = 2;
            primes[1] = 3;
            return primes;
        }
        
        for (int i = 2; i < Math.sqrt(primesCount); i++) {
            if (primesStatus[i]) {
                for (int j = i * i; j <= primesCount; j += i) {
                    primesStatus[j] = false;
                }
            }
        }

        for (int i = 2; i <= primesCount; i++) {
            if (primesStatus[i]) {
                primes[i] = i;
                System.out.println(primes[i]);
            }
        }

        return primes;
    }
}
