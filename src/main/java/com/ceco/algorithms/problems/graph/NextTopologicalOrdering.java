package com.ceco.algorithms.problems.graph;

import com.ceco.algorithms.datastructure.list.ArrayList;
import com.ceco.algorithms.datastructure.list.List;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 19-Jan-2017
 */
public class NextTopologicalOrdering {

    private static final class Graph {

        Map<Integer, Node> adjList;

        Graph() {
            this.adjList = new HashMap<>();
        }

        Node getNode(int nodeName) {
            return adjList.get(nodeName);
        }

        void addNode(int nodeName) {
            Node node = new Node(nodeName);
            adjList.putIfAbsent(nodeName, node);
        }
    }

    private static class Node {

        int name;
        List<Node> edges;

        Node(int name) {
            this.name = name;
            this.edges = new ArrayList<>();
        }

        public void addEdge(Node edge) {
            this.edges.add(edge);
        }
    }

    public static void main(String args[]) {
        Graph graph = new Graph();

        Scanner scanner = new Scanner(System.in);
        int nodeCount = scanner.nextInt();
        int edgeCount = scanner.nextInt();

        int[] nodeNames = IntStream.range(1, nodeCount + 1).toArray();
        for (int nName : nodeNames) {
            graph.addNode(nName);
        }

        for (int i = 0; i < edgeCount; i++) {
            int left = scanner.nextInt();
            int right = scanner.nextInt();

            Node leftNode = graph.getNode(left);
            Node rightNode = graph.getNode(right);
            if (rightNode != null) {
                leftNode.addEdge(rightNode);
            } else {
                leftNode.addEdge(new Node(right));
            }
        }
    }
}
