package com.ceco.algorithms.unionfind;


import java.util.concurrent.TimeUnit;

/**
 * Main execution class for Union-Find implementations.
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 10 Feb 2015
 */
public class Main {

    public static void main(String[] args) {

        long startTime = System.nanoTime();

        UnionFind<Integer> quickFind = new QuickFind(10);
        quickFind.union(1, 2);
        quickFind.union(2, 3);
        quickFind.union(3, 5);
        System.out.println(quickFind.isConnected(1, 2));
        System.out.println(quickFind.isConnected(1, 3));
        System.out.println(quickFind.isConnected(2, 5));

        long endTime = System.nanoTime();
        System.out.println("QuickFind:\t\t" + TimeUnit.NANOSECONDS.toMicros(endTime - startTime));

        startTime = System.nanoTime();

        UnionFind<Integer> quickUnion = new QuickUnion(10);
        quickUnion.union(1, 2);
        quickUnion.union(2, 3);
        quickUnion.union(3, 5);
        System.out.println(quickUnion.isConnected(1, 2));
        System.out.println(quickUnion.isConnected(1, 3));
        System.out.println(quickUnion.isConnected(2, 5));

        endTime = System.nanoTime();
        System.out.println("QuickUnion:\t\t" + TimeUnit.NANOSECONDS.toMicros(endTime - startTime));

        startTime = System.nanoTime();

        UnionFind<Integer> weightedQuickUnion = new WeightedQuickUnion(10);
        weightedQuickUnion.union(1, 2);
        weightedQuickUnion.union(2, 3);
        weightedQuickUnion.union(3, 5);
        System.out.println(weightedQuickUnion.isConnected(1, 2));
        System.out.println(weightedQuickUnion.isConnected(1, 3));
        System.out.println(weightedQuickUnion.isConnected(2, 5));

        endTime = System.nanoTime();
        System.out.println("WeightedQuickUnion:\t\t" + TimeUnit.NANOSECONDS.toMicros(endTime - startTime));
    }
}
