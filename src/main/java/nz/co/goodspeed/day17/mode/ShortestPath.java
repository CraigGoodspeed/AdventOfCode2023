package nz.co.goodspeed.day17.mode;

import nz.co.goodspeed.AppStartup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class ShortestPath  {

    static List<Node> steps = new ArrayList<>();

    public static int[][] findShortestPath(int[][] grid) {
        int n = grid.length;

        // Distance matrix to store tentative distances
        int[][] distances = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }

        // Visited boolean array
        boolean[][] visited = new boolean[n][n];

        // Priority queue for unvisited nodes (PriorityQueue in Java uses natural ordering)
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // Set distance of starting node to 0
        distances[0][0] = 0;
        pq.add(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // If already visited, skip
            if (visited[current.x][current.y]) {
                continue;
            }

            // Mark visited
            visited[current.x][current.y] = true;

            // Check all four neighbors
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    // at this point, have i used the same x or y 3 times?

                    int newX = current.x + dx;
                    int newY = current.y + dy;


                    // Check valid bounds and unvisited
                    if (isValid(newX, newY, n) && !visited[newX][newY]) {
                        int newDistance = current.distance + grid[newX][newY];

                        // Update distance if shorter
                        if (newDistance < distances[newX][newY] && lessThanThreeSteps(newX, newY)) {
                            distances[newX][newY] = newDistance;
                            pq.add(new Node(newX, newY, newDistance));
                        }
                        else {
                            steps = new ArrayList<>();
                        }
                    }
                }
            }
        }

        return distances;
    }

    static boolean lessThanThreeSteps(int x, int y) {
        if(!steps.isEmpty()){
            Node i = steps.get(0);
            boolean allXs = steps.stream().filter(t -> t.x == i.x ).count() >= 3;
            boolean allYs = steps.stream().filter(t -> t.y == i.y).count() >= 3;
            if((allXs || allYs) && steps.size() >= 3) {
                return false;
            }
        }
        steps.add(new Node(x,y,0));
        return true;
    }

    private static boolean isValid(int x, int y, int n) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    static class Node implements Comparable<Node> {
        int x, y, distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static void  main(String[] args) throws IOException {

        String rawData = AppStartup.readAll("/home/craig/dev/AdventOfCode2023/input/day17/test.txt");
        Map map = new Map(rawData);

        int[][] grid = map.getHeatLostOnThisBlock();

        int[][] distances = findShortestPath(grid);

        // Print the shortest distance from top left to bottom right
        System.out.println("Shortest distance: " + distances[grid.length - 1][grid.length - 1]);
    }
}