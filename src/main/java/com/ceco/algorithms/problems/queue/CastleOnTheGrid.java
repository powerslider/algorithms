package com.ceco.algorithms.problems.queue;

import java.util.*;

/**
 * @see <a href="https://www.hackerrank.com/challenges/castle-on-the-grid">
 *          Castle on the grid
 *     </a>
 *
 *
 * Example input:
 * 3
 * .X.
 * .X.
 * ...
 * 0 0 0 2
 *
 * Output:
 * 3
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 18-Sep-2016
 */
public class CastleOnTheGrid {

    private static final char VISITED = '#';
    private static final char OBSTACLE = 'X';

    private static class Node {
        int x;
        int y;
        Direction direction;
        Node parent;
        List<Node> neighbours;

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", direction=" + direction +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        enum Direction {
            START, NORTH, SOUTH, EAST, WEST;
        }

        Node(int x, int y) {
            this(x, y, null, null);
        }

        Node(int x, int y, Direction direction, Node parent) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.parent = parent;
        }

        boolean isNeighbour(Node node) {
            if (neighbours != null) {
                return neighbours.stream().anyMatch((n) -> n.equals(node));
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int gridSize = scanner.nextInt();
        scanner.nextLine();

        char[][] grid = new char[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            String row = scanner.nextLine();
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = row.toCharArray()[j];
            }
        }

        int startX = scanner.nextInt();
        int startY = scanner.nextInt();
        int endX = scanner.nextInt();
        int endY = scanner.nextInt();

        Node source = new Node(startX, startY, Node.Direction.START, null);
        Node target = new Node(endX, endY);

        System.out.println(breadthFirstSearch(grid, source, target));
    }

    private static int breadthFirstSearch(char[][] grid, Node source, Node target) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(source);

        grid[source.x][source.y] = VISITED;
        List<Node> visited = new ArrayList<>();
        visited.add(source);

        int distance = 0;

        outer:
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            distance++;

            List<Node> neighbours = getNeighbours(current, grid);
            current.neighbours = neighbours;
            visited.add(current);

            for (Node neighbour: neighbours) {
                if (!visited.contains(neighbour)) {
                    if (neighbour.equals(target)) {
                        break outer;
                    }

                    grid[neighbour.x][neighbour.y] = VISITED;
                    visited.add(neighbour);

                    queue.offer(neighbour);
                }
            }
        }

        List<Node> shortestPathList = new ArrayList<>();
        Node currentNode = source;

        for (Node node : visited) {
            Node parent = node.parent;
            if (parent != null && parent.equals(currentNode)) {
                System.out.println(node);
                currentNode = node;
                shortestPathList.add(node);
//                if (node.equals(target)) break;
            }
        }
        return distance;
    }

    private static List<Node> getNeighbours(Node current, char[][] grid) {
        List<Node> neighbours = new ArrayList<>();

        // west
        int westX = current.x;
        int westY = current.y - 1;
        if (current.y > 0 && isNotVisitedAndNotObstacle(grid[westX][westY])) {
            neighbours.add(new Node(westX, westY, Node.Direction.WEST, current));
        }
        // south
        int southX = current.x + 1;
        int southY = current.y;
        if (current.x < grid.length - 1 && isNotVisitedAndNotObstacle(grid[southX][southY])) {
            neighbours.add(new Node(southX, southY, Node.Direction.SOUTH, current));
        }
        // east
        int eastX = current.x;
        int eastY = current.y + 1;
        if (current.y < grid[0].length - 1 && isNotVisitedAndNotObstacle(grid[eastX][eastY])) {
            neighbours.add(new Node(eastX, eastY, Node.Direction.EAST, current));
        }
        // north
        int northX = current.x - 1;
        int northY = current.y;
        if (current.x > 0 && isNotVisitedAndNotObstacle(grid[northX][northY])) {
            neighbours.add(new Node(northX, northY, Node.Direction.NORTH, current));
        }

        return neighbours;
    }

    private static boolean isNotVisitedAndNotObstacle(char c) {
        return c != VISITED && c != OBSTACLE;
    }
}
