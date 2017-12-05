package com.ceco.algorithms.problems.warmup;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/compare-the-triplets">
 *          Compare The Triplets
 *     </a>
 * <p>
 * Example input:
 * 5 6 7
 * 3 6 10
 * <p>
 * Output:
 * 1 1
 * <p>
 * @author Tsvetan Dimitrov (tsvetan.dimitrov23@gmail.com)
 * @version 0.1.0
 * @since 05-Dec-2017
 */
public class CompareTheTriplets {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[] aliceTriplet = readTriplet(scanner);
		int[] bobTriplet = readTriplet(scanner);

		int bobScore = 0;
		int aliceScore = 0;

		for (int i = 0; i < 3; i++) {
			if (aliceTriplet[i] > bobTriplet[i])
				aliceScore++;
			else if (bobTriplet[i] > aliceTriplet[i])
				bobScore++;
		}

		System.out.println(aliceScore + " " + bobScore);
	}

	private static int[] readTriplet(final Scanner scanner) {
		int[] triplet = new int[3];
		for (int i = 0; i < triplet.length; i++) {
			triplet[i] = scanner.nextInt();
		}
		return triplet;
	}
}
