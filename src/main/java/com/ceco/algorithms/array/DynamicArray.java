package com.ceco.algorithms.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Example input:
 * 2 5
 * 1 0 5
 * 1 1 7
 * 1 0 3
 * 2 1 0
 * 2 1 1
 *
 * Output:
 * 7
 * 3
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 31-Jul-2016
 */
public class DynamicArray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strNums = scanner.nextLine().split("\\s");
        int numSeq = Integer.parseInt(strNums[0]);
        int numQueries = Integer.parseInt(strNums[1]);

        List<List<Integer>> seqList = new ArrayList<>(numSeq);
        for (int i = 0; i < numSeq; i++) {
            seqList.add(new ArrayList<Integer>());
        }

        int[][] queries = new int[numQueries][3];

        int lastAns = 0;

        int queriesRows = queries.length;
        int queriesCols = queries[0].length;

        for (int row = 0; row < queriesRows; row++) {
            String[] strArgs = scanner.nextLine().split("\\s");
            for (int col = 0; col < queriesCols; col++) {
                queries[row][col] = Integer.parseInt(strArgs[col]);
            }
        }

        for (int[] queryRow : queries) {
            int queryType = queryRow[0];
            int x = queryRow[1];
            int y = queryRow[2];
            int seqIdx = (x ^ lastAns) % numSeq;

            List<Integer> seq = seqList.get(seqIdx);
            switch (queryType) {
                case 1:
                    seq.add(y);
                    break;
                case 2:
                    lastAns = seq.get(y % seq.size());
                    System.out.println(lastAns);
                    break;
            }
        }
    }
}
