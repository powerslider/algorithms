package com.ceco.algorithms.problems.heap;

import java.util.*;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 17-Oct-2016
 */
public class MinimumAverageWaitingTime {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int customerCount = scanner.nextInt();
        scanner.nextLine();

        Queue<Map.Entry<Long, Long>> minHeap = new PriorityQueue<>(
                (a, b) ->  {
                    Long aValue = a.getValue();
                    Long bValue = b.getValue();
                    return aValue.compareTo(bValue) < 0 ? -1 : Objects.equals(aValue, bValue) ? 0 : 1;
                });


        for (int i = 0; i < customerCount; i++) {
            Map.Entry<Long, Long> entry = new AbstractMap.SimpleEntry<>(
                    scanner.nextLong(), scanner.nextLong());
            minHeap.offer(entry);
        }

        long customerServiceAvg = 0;
        long customerServiceAvgDelays = 0;

        for (long i = 0; !minHeap.isEmpty(); i++) {
            Map.Entry<Long, Long> entry = minHeap.poll();
            Long arrivalTime = entry.getKey();
            Long cookTime = entry.getValue();
            if (i > 0) {
                customerServiceAvgDelays += Math.abs(customerServiceAvg - (arrivalTime * i));
                customerServiceAvg += cookTime + customerServiceAvgDelays;
            } else {
                customerServiceAvg += cookTime;
            }
        }

        System.out.println(customerServiceAvg / customerCount);
    }
}
