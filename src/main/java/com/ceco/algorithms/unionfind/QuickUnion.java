package com.ceco.algorithms.unionfind;

/**
 * Union-Find implementation based on the rule that the id of each site is the name of another
 * site in the same component (possibly itself) — we refer to this connection as a link. This
 * interpretation of the site ids forms a tree structure with clearly defined links to parents
 * and children. Two sites share the same component if they have the same tree root.
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * <p/>
 * Date added: 2015-02-10
 */
public class QuickUnion implements UnionFind<Integer> {

    /**
     * Site-indexed array.
     */
    private Integer[] id;

	public QuickUnion(int count) {
		this.id = new Integer[count];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
	}

    @Override
    public void union(Integer p, Integer q) {
        // Put p and q into the same component
        int pRoot = find(p);
        int qRoot = find(q);

        // Nothing to do if p and q are already in the same component.
        if (pRoot == qRoot) return;

        // Give p and q the same root.
        id[pRoot] = qRoot;
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
