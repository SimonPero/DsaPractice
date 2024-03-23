package IntentosLeetcode.graphs.distinctIslands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class islands {
    private List<String> islandPatterns = new ArrayList<>(); // List to store island patterns

    public int numDistinctIslands(char[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        boolean[][] visited = new boolean[numRows][numCols];
        int numIslands = 0;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    StringBuilder movementPattern = new StringBuilder();
                    bfs(i, j, grid, visited, movementPattern); // Start BFS from the current cell
                    String pattern = movementPattern.toString();
                    if (!islandPatterns.contains(pattern)) {
                        islandPatterns.add(pattern); // Store the unique island pattern
                        numIslands++; // Increment the count of distinct islands
                    }
                }
            }
        }
        return numIslands;
    }

    private void bfs(int row, int col, char[][] grid, boolean[][] visited, StringBuilder movementPattern) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { row, col });
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];

            // Perform BFS in four directions
            bfsUtil(x + 1, y, grid, visited, queue, numRows, numCols, movementPattern, "down");
            bfsUtil(x - 1, y, grid, visited, queue, numRows, numCols, movementPattern, "up");
            bfsUtil(x, y + 1, grid, visited, queue, numRows, numCols, movementPattern, "right");
            bfsUtil(x, y - 1, grid, visited, queue, numRows, numCols, movementPattern, "left");
        }
    }

    private void bfsUtil(int x, int y, char[][] grid, boolean[][] visited, Queue<int[]> queue,
            int numRows, int numCols, StringBuilder movementPattern, String direction) {
        if (x >= 0 && x < numRows && y >= 0 && y < numCols && grid[x][y] == '1' && !visited[x][y]) {
            queue.offer(new int[] { x, y });
            visited[x][y] = true;
            movementPattern.append(direction); // Append the direction to the movement pattern
        }
    }

    public static void main(String[] args) {
        islands island = new islands(); 
        char[][] grid = {
            {'1', '1', '0', '0', '0', '1', '1'},
            {'1', '0', '0', '0', '0', '1', '1'},
            {'0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0'},
            {'1', '1', '0', '0', '0', '1', '1'},
            {'1', '0', '0', '0', '0', '1', '1'},
            {'1', '0', '0', '0', '0', '0', '0'}
        };
    
        int distinctIslands = island.numDistinctIslands(grid);
        System.out.println("Number of distinct islands: " + distinctIslands);
    }
}

