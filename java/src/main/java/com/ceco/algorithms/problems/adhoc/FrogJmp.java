package com.ceco.algorithms.problems.adhoc;

import java.util.Scanner;

/**
 * @see <a href= "https://codility.com/programmers/lessons/3-time_complexity/frog_jmp/">
 *          FrogJmp
 *     </a>
 * <p>
 * Example input:
 * 3
 * 10 85 30
 * <p>
 * Output:
 * 3
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 05-Nov-2016
 */
public class FrogJmp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int start = scanner.nextInt();
        int end = scanner.nextInt();
        int step = scanner.nextInt();

        int dist = end - start;
        int jumps = dist / step;
        if (dist % step != 0) {
            jumps++;
        }
        System.out.println(jumps);
    }
}
