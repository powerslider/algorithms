package com.ceco.algorithms.problems.heap;

import java.util.*;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 17-Oct-2016
 */
public class MinimumAverageWaitingTime {

    private static class Customer {

        long arrivalTime;
        long cookingTime;
        long totalTime;

        Customer(long arrivalTime, long cookingTime) {
            this.arrivalTime = arrivalTime;
            this.cookingTime = cookingTime;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int customerCount = scanner.nextInt();
        scanner.nextLine();

        Queue<Customer> customerHeap = new PriorityQueue<>(
                (a, b) ->  {
                    long aCooking = a.cookingTime;
                    long bCooking = b.cookingTime;
                    return aCooking < bCooking ? -1 : Objects.equals(aCooking, bCooking) ? 0 : 1;
                });

        Deque<Customer> allCustomers = new ArrayDeque<>();

        for (int i = 0; i < customerCount; i++) {
            Customer customer = new Customer(scanner.nextLong(), scanner.nextLong());
            customerHeap.offer(customer);
        }

        long totalTime = 0;
        long waitingTime = 0;
        long prevCustomersTotalTime = 0;
        long prevCustomerCount = 0;

        for (long i = 0; !customerHeap.isEmpty(); i++) {
            Customer customer = customerHeap.poll();
            long currentArrivalTime = customer.arrivalTime;
            long currentCookingTime = customer.cookingTime;
            if (i > 0) {
                while (!allCustomers.isEmpty()) {
                    prevCustomersTotalTime += allCustomers.pop().totalTime;
                    prevCustomerCount++;
                }
                waitingTime = prevCustomersTotalTime - (currentArrivalTime * prevCustomerCount);
                customer.totalTime = currentCookingTime + waitingTime;
            } else {
                customer.totalTime = currentCookingTime;
            }
            totalTime += customer.totalTime;
            allCustomers.push(customer);
        }

        System.out.println(totalTime / customerCount);
    }
}
