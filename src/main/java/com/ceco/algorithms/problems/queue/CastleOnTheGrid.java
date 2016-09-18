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

        enum Direction {
            NORTH, SOUTH, EAST, WEST
        }

        Node(int x, int y) {
            this(x, y, null);
        }

        Node(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
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

        Node source = new Node(startX, startY);
        Node target = new Node(endX, endY);

        System.out.println(breadthFirstSearch(grid, source, target));
    }

    private static int breadthFirstSearch(char[][] grid, Node source, Node target) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(source);
        grid[source.x][source.y] = VISITED;

        List<Node.Direction> changedDirections = new ArrayList<>();

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            List<Node> neighbours = getNeighbours(current, grid);
            for (Node neighbour: neighbours) {
                if (neighbour.x == target.x && neighbour.y == target.y) {
                    return changedDirections.size();
                }
                queue.offer(neighbour);
                changedDirections.add(neighbour.direction);
                grid[neighbour.x][neighbour.y] = VISITED;
            }
        }
        return 0;
    }

    private static List<Node> getNeighbours(Node current, char[][] grid) {
        List<Node> neighbours = new ArrayList<>();

        // north
        int northX = current.x;
        int northY = current.y - 1;
        if (current.y > 0 && isNotVisitedAndNotObstacle(grid[northX][northY])) {
            neighbours.add(new Node(northX, northY, Node.Direction.NORTH));
        }
        // east
        int eastX = current.x + 1;
        int eastY = current.y;
        if (current.x < grid.length - 1 && isNotVisitedAndNotObstacle(grid[eastX][eastY])) {
            neighbours.add(new Node(eastX, eastY, Node.Direction.EAST));
        }
        // south
        int southX = current.x;
        int southY = current.y + 1;
        if (current.y < grid[0].length - 1 && isNotVisitedAndNotObstacle(grid[southX][southY])) {
            neighbours.add(new Node(southX, southY, Node.Direction.SOUTH));
        }
        // west
        int westX = current.x - 1;
        int westY = current.y;
        if (current.x > 0 && isNotVisitedAndNotObstacle(grid[westX][westY])) {
            neighbours.add(new Node(westX, westY, Node.Direction.WEST));
        }

        return neighbours;
    }

    private static boolean isNotVisitedAndNotObstacle(char c) {
        return c != VISITED && c != OBSTACLE;
    }
}
