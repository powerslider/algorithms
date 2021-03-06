package com.ceco.algorithms.unionfind;

/**
 * Union-Find implementation based on {@link QuickUnion} with the optimization of keeping a balanced tree.
 * The size of the tree for each site is tracked and the union step is always applied by guaranteeing that
 * the smaller tree's root points to the larger one.
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 10 Feb 2015
 */
public class WeightedQuickUnion implements UnionFind<Integer> {

    /**
     * Site-indexed array.
     */
    private Integer[] id;

    /**
     * An array that keeps the children count of each node in order when conducting a union operation
     * to avoid having an imbalanced tree for performance purposes.
     */
    private int[] sz;

    public WeightedQuickUnion(int count) {
        this.id = new Integer[count];
        this.sz = new int[count];
        for (int i = 0; i < id.length; i++) {
            //set each node id with the index number
            id[i] = i;

            //set size of neighbours to 1 for each tree node
            sz[i] = 1;
        }
    }

    @Override
    public void union(Integer p, Integer q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        //Make smaller root point to larger one
        if (sz[rootP] < sz[rootQ]) {
            id[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
        } else {
            id[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
        }
    }

    @Override
    public Integer find(Integer p) {
        // Follow all sites in a component
        // until you find the root that links to itself.
        while (!p.equals(id[p])) {
            p = id[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(Integer p, Integer q) {
        return find(p).equals(find(q));
    }
}
