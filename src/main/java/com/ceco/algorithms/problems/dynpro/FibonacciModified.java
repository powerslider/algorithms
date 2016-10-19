package com.ceco.algorithms.problems.dynpro;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/fibonacci-modified">
 *          Fibonacci Modified
 *     </a>
 * <p>
 * Example input:
 * 0 1 5
 * <p>
 * Output:
 * 5
 * <p>
 * Example input:
 * 0 1 10
 * <p>
 * Output:
 * 84266613096281243382112
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 19-Oct-2016
 */
public class FibonacciModified {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger t1 = scanner.nextBigInteger();
        BigInteger t2 = scanner.nextBigInteger();
        int n = scanner.nextInt();
        System.out.println(fibMod(t1, t2, n));
    }

    private static BigInteger fibMod(BigInteger t1, BigInteger t2, int n) {
        n--;
        if (n == 1)
            return t2;
        else
            return fibMod(t2, t2.pow(2).add(t1), n);
    }
}
