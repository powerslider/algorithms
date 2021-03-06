package com.ceco.algorithms.unionfind;


/**
 * Union-Find implementation based on the rule that all sites in a component must have the same id.
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 10 Feb 2015
 */
public class QuickFind implements UnionFind<Integer> {

    /**
     * Site-indexed array.
     */
    private Integer[] id;

    public QuickFind(int count) {
        this.id = new Integer[count];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    @Override
    public Integer find(Integer p) {
        return id[p];
    }

    @Override
    public boolean isConnected(Integer p, Integer q) {
        return (id[p].equals(id[q]));
    }

    @Override
    public void union(Integer p, Integer q) {
        // Put p and q into the same component
        int pid = id[p];
        int qid = id[q];

        // Nothing to do if p and q are already in the same component.
        if (pid == qid) return;

        // Rename p’s component to q’s name.
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }
}
