package com.ceco.algorithms.unionfind;


/**
 * Dynamic connectivity.
 * <p/>
 * The input is a sequence of pairs of objects of some type and we are to interpret the pair p q
 * as meaning of p is connected to q. We assume that "is connected to" is an equivalence relation:
 * <p/>
 * <ul>
 * <li>Symmetric: If p is connected to q, then q is connected to p.</li>
 * <li>Transitive: If p is connected to q and q is connected to r, then p is connected to r.</li>
 * <li>Reflexive: p is connected to p.</li>
 * <ul/>
 * <p/>
 * An equivalence relation partitions the objects into equivalence classes or connected components.
 * <p/>
 * Union-Find is an algorithm that filters out extraneous pairs from the sequence and ignores pairs
 * that are already connected.
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 10 Feb 2015
 */
public interface UnionFind<T> {

    /**
     * Operation defining connectivity between 2 elements.
     *
     * @param p first element
     * @param q second element
     */
    void union(T p, T q);


    /**
     * Operation defining which site to which component belongs.
     *
     * @param p
     * @return
     */
    T find(T p);

    /**
     * Checks if 2 elements are connected in a pair.
     *
     * @param p first element
     * @param q second element
     * @return true or false
     */
    boolean isConnected(T p, T q);
}
