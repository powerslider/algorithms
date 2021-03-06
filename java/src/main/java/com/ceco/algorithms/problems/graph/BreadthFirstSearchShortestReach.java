package com.ceco.algorithms.problems.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/bfsshortreach">
 *          Breadth First Search: Shortest Reach
 *     </a>
 * <p>
 * Example input:
 * 2
 * 4 2
 * 1 2
 * 1 3
 * 1
 * 3 1
 * 2 3
 * 2
 * <p>
 * Output:
 * 6 6 -1
 * -1 6
 * <p>
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 30-Jan-2017
 */
public class BreadthFirstSearchShortestReach {

    private static class Node {

        int name;

        List<Node> edges;

        Node parent;

        Node(int name) {
            this.name = name;
            this.edges = new ArrayList<>();
        }

        void addEdge(Node edge) {
            edge.parent = this;
            this.edges.add(edge);
        }
    }

    private static class Graph {

        static final int EDGE_WEIGHT = 6;

        Map<Integer, Node> adjList = new HashMap<>();

        void addNodeIfAbsent(int nodeName) {
            adjList.putIfAbsent(nodeName, new Node(nodeName));
        }

        Node getNode(int nodeName) {
            return adjList.get(nodeName);
        }

        int size() {
            return adjList.size();
        }
    }
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int queryCount = scanner.nextInt();
        for (int i = 0; i < queryCount; i++) {
            Graph g = new Graph();
            int nodeCount = scanner.nextInt();
            for (int j = 1; j <= nodeCount; j++) {
                g.addNodeIfAbsent(j);
            }

            int edgeCount = scanner.nextInt();
            for (int j = 0; j < edgeCount; j++) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                Node fromNode = g.getNode(from);
                Node toNode = g.getNode(to);
                fromNode.addEdge(toNode);
            }
            int start = scanner.nextInt();
            Node startNode = g.getNode(start);

            int[] distances = shortestReach(g, startNode);
            String distancesStr = IntStream.of(distances)
                    .filter(d -> d != 0)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));
            System.out.println(distancesStr);
        }
    }

    private static int[] shortestReach(Graph g, Node startNode) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(startNode);

        int[] distances = new int[g.size()];
        Arrays.fill(distances, -1);
        distances[startNode.name - 1] = 0;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            for (Node n : currentNode.edges) {
                if (distances[n.name - 1] == -1) {
                    queue.offer(n);
                    distances[n.name - 1] = distances[currentNode.name - 1] + Graph.EDGE_WEIGHT;
                }
            }
        }

        return distances;
    }
}
