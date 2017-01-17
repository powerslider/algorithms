package com.ceco.algorithms.problems.graph;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/journey-to-the-moon">
 *          Journey to the Moon
 *     </a>
 * <p>
 * Explanation: <a href="https://www.youtube.com/watch?v=ID00PMy0-vE"></a>
 * <p>
 * Example input:
 * 4 2
 * 0 1
 * 2 3
 * <p>
 * Output:
 * 4
 * <p>
 * Example input:
 * 10 7
 * 0 2
 * 1 8
 * 1 4
 * 2 8
 * 2 6
 * 3 5
 * 6 9
 * <p>
 * Output:
 * 23
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 17-Jan-2017
 */
public class JourneyToTheMoon {

    private static class DisjointSet {

        private Map<Integer, Node> map = new HashMap<>();

        private static class Node {

            int name;

            int rank;

            Node parent;

            Node(int name) {
                this.name = name;
                this.parent = this;
            }
        }

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

    public static void main(String[] args) {
        DisjointSet disjointSet = new DisjointSet();

        Scanner scanner = new Scanner(System.in);
        int nodeValLimit = scanner.nextInt();

        int nodePairs = scanner.nextInt();
        scanner.nextLine();

        int[] nodeVals = IntStream.range(0, nodeValLimit).toArray();
        for (int nodeName : nodeVals) {
            disjointSet.makeSet(nodeName);
        }

        for (int i = 0; i < nodePairs; i++) {
            int left = scanner.nextInt();
            int right = scanner.nextInt();

            disjointSet.union(left, right);
        }

        List<Integer> setsRepresentativesList = new ArrayList<>();
        for (int i = 0; i < nodeValLimit; i++) {
            int setRepresentative = disjointSet.findSet(i).name;
            setsRepresentativesList.add(setRepresentative);
        }
        Set<Integer> setsRepresentativesSet = new HashSet<>(setsRepresentativesList);
        int sum = 0;
        int total = 0;
        for (int r : setsRepresentativesSet) {
            int occurences = Collections.frequency(setsRepresentativesList, r);
            total = total + (sum * occurences);
            sum += occurences;
        }

        System.out.println(total);
    }
}
