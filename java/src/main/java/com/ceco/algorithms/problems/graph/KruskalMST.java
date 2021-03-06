package com.ceco.algorithms.problems.graph;

import java.util.*;

/**
 * @see <a href="https://www.hackerrank.com/challenges/kruskalmstrsub">
 *          Kruskal(MST) - Really Special Subtree
 *     </a>
 * <p>
 * Example input:
 * 4 6
 * 1 2 5
 * 1 3 3
 * 4 1 6
 * 2 4 7
 * 3 2 4
 * 3 4 5
 * <p>
 * Output:
 * 12
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 17-Jan-2017
 */
public class KruskalMST {

    private static class DisjointSet {

        private Map<Integer, Node> map = new HashMap<>();

        public int size() {
            return map.size();
        }

        /**
         * Add each node as a one element set.
         *
         * @param nodeName
         *      name of node to add
         */
        public void makeSet(int nodeName) {
            Node node = new Node(nodeName);
            map.putIfAbsent(nodeName, node);
        }

        /**
         * Connect two nodes and set top level parent on each.
         * @param a
         *      name of first node
         * @param b
         *      name of second node
         * @return true/false if succeeded
         */
        public boolean union(int a, int b) {
            Node node1 = map.get(a);
            Node node2 = map.get(b);

            Node parent1 = findSet(node1);
            Node parent2 = findSet(node2);

            if (parent1.name == parent2.name)
                return false;

            if (parent1.rank >= parent2.rank) {
                parent1.rank =
                        (parent1.rank == parent2.rank)
                                ? parent1.rank + 1
                                : parent1.rank;

                parent2.parent = parent1;
            } else {
                parent1.parent = parent2;
            }

            return true;
        }

        /**
         * Get node representative of the disjoint set.
         *
         * @param nodeName
         *      node name of node to be checked for its set representative
         * @return set representative node
         */
        public Node findSet(int nodeName) {
            return findSet(map.get(nodeName));
        }

        private Node findSet(Node node) {
            Node parent = node.parent;
            if (parent.equals(node)) {
                return parent;
            }
            node.parent = findSet(node.parent);

            return node.parent;
        }

    }

    private static class Node {

        int name;
        int rank;
        Node parent;

        Node(int name) {
            this.name = name;
            this.parent = this;
        }
    }

    private static class Edge {

        int ingoingNode;
        int outgoingNode;
        int weight;

        Edge(int ingoingNode, int outgoingNode, int weight) {
            this.ingoingNode = ingoingNode;
            this.outgoingNode = outgoingNode;
            this.weight = weight;
        }
    }

    private static List<Edge> getMinSpanningTree(DisjointSet disjointSet, List<Edge> edges) {
        List<Edge> resultEdges = new ArrayList<>();

        // sort edge weights ascendingly
        edges.sort(Comparator.comparingInt(o -> o.weight));

        for (Edge e : edges) {
            int root1 = disjointSet.findSet(e.ingoingNode).name;
            int root2 = disjointSet.findSet(e.outgoingNode).name;

            // if root parents are different join the nodes into one set
            if (root1 != root2) {
                disjointSet.union(e.ingoingNode, e.outgoingNode);
                resultEdges.add(e);
            }
        }
        return resultEdges;
    }

    public static void main(String[] args) {
        DisjointSet disjointSet = new DisjointSet();

        List<Edge> edges = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int nodeCount = scanner.nextInt();
        int edgeCount = scanner.nextInt();

        for (int i = 0; i < edgeCount; i++) {
            int ingoingNode = scanner.nextInt();
            int outgoingNode = scanner.nextInt();
            int edgeWeight = scanner.nextInt();

            disjointSet.makeSet(ingoingNode);
            disjointSet.makeSet(outgoingNode);

            edges.add(new Edge(ingoingNode, outgoingNode, edgeWeight));
        }

        List<Edge> minSpanningTree = getMinSpanningTree(disjointSet, edges);
        int mstWeightSum = minSpanningTree.stream().mapToInt((e) -> e.weight).sum();
        System.out.println(mstWeightSum);
    }
}
