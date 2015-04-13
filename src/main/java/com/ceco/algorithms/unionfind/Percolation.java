package com.ceco.algorithms.unionfind;


import com.ceco.algorithms.unionfind.WeightedQuickUnion;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * </p>
 * Date added 9/6/14
 */

public class Percolation {

    private static final int ADDITIONAL_VIRTUAL_SITES = 2;

    /**
     * Length of one dimension of the grid.
     */
    private final int N;

    /**
     * A common site on the top side of the grid.
     */
    private final int virtualTop;

    /**
     * A common site on the bottom side of the grid.
     */
    private final int virtualBottom;

    /**
     * A marker array for opening sites.
     */
    private boolean[] openSites;


    private WeightedQuickUnion grid;

    /**
     * Create N-by-N grid, with all sites blocked.
     */
    public Percolation(int N) {
        this.N = N;
        openSites = new boolean[N*N];
        grid = new WeightedQuickUnion(N*N + ADDITIONAL_VIRTUAL_SITES);
        virtualTop = getSiteIndex(N, N) + 1;
        virtualBottom = getSiteIndex(N, N) + 2;
    }

    /**
     * Create indexes for the sites using their position in the grid.
     * @param row
     * @param col
     * @return site index
     */
    private int getSiteIndex(int row, int col) {
        if (row <= 0 || row > N || col <= 0 || col > N) {
            throw new IndexOutOfBoundsException(
                    "(" + row + ", " + col + ") out of bounds " + "for " + N + "^2 grid.");
        }
        return (row - 1) * N + (col - 1);
    }

    /**
     * Open site if it is not already opened.
     * @param i row
     * @param j col
     */
    public void open(int i, int j) {
        if (isOpen(i, j)) {
            return;
        }

        int index = getSiteIndex(i, j);
        openSites[index] = true;

        // At first row connect virtual top site with current opened site.
        if (i == 1) {
            grid.union(virtualTop, index);
        }

        // At last row connect virtual bottom site with last opened site.
        if (i == N) {
            grid.union(virtualBottom, index);
        }

        // Connect right opened neighbour.
        if (i < N && isOpen(i + 1, j)) {
            grid.union(getSiteIndex(i + 1, j), index);
        }

        // Connect left opened neighbour.
        if (i > 1 && isOpen(i - 1, j)) {
            grid.union(getSiteIndex(i - 1, j), index);
        }

        // Connect bottom opened neighbour.
        if (j < N && isOpen(i, j + 1)) {
            grid.union(getSiteIndex(i, j + 1), index);
        }

        // Connect top opened neighbour.
        if (j > 1 && isOpen(i, j - 1)) {
            grid.union(getSiteIndex(i, j - 1), index);
        }
    }

    /**
     * Is site open?
     * @param i row
     * @param j column
     */
    public boolean isOpen(int i, int j) {
        return openSites[getSiteIndex(i, j)];
    }

    /**
     * Is site full?
     * "Full" means the site is open and it is connected to the virtual top site!
     * @param i row
     * @param j column
     */
    public boolean isFull(int i, int j) {
        return isOpen(i, j) && grid.isConnected(virtualTop, getSiteIndex(i, j));
    }

    /**
     * Does system percolate?
     */
    public boolean percolates() {
        return grid.isConnected(virtualTop, virtualBottom);
    }
}
