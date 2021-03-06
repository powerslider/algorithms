//package com.ceco.algorithms;
//
//
///**
// * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
// * <p/>
// * Date added 9/7/14
// */
//public class PercolationStats {
//
//    private double [] attemps;
//
//    // Perform T independent computational experiments on an N-by-N grid.
//    public PercolationStats(int N, int T){
//        if (N <= 0||T <= 0) {
//            throw new IllegalArgumentException("Please supply values greater than 0!");
//        }
//        attemps = new double [T];
//        for(int i = 0;i < T;i++) {
//            Percolation percolation = new Percolation(N);
//            int steps=0;
//            while(!percolation.percolates()) {
//                int row = StdRandom.uniform(N) + 1;
//                int column = StdRandom.uniform(N) + 1;
//                if(!percolation.isOpen(row,column)) {
//                    percolation.open(row,column);
//                    steps++;
//                }
//            }
//            attemps[i] = (double)steps / (N * N);
//        }
//    }
//    // sample mean of percolation threshold
//    public double mean(){
//        return StdStats.mean(attemps);
//    }
//    // sample standard deviation of percolation threshold
//    public double stddev(){
//        return StdStats.stddev(attemps);
//    }
//    // returns lower bound of the 95% confidence interval
//    public double confidenceLo(){
//        return mean() - ((1.96 * stddev()) / Math.sqrt(attemps.length));
//    }
//    // returns upper bound of the 95% confidence interval
//    public double confidenceHi(){
//        return mean() + ((1.96 * stddev()) / Math.sqrt(attemps.length));
//    }
//    // test client, described below
//    public static void main(String[] args){
//        PercolationStats ps=new PercolationStats(300,1000);
//        StdOut.print("mean = "+ps.mean()+"\n");
//        StdOut.print("std dev = "+ps.stddev()+"\n");
//        StdOut.print("95% confidence interval = "+ps.confidenceLo()+", "+ps.confidenceHi());
//    }
//}
