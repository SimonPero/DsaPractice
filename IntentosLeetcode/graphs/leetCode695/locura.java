package IntentosLeetcode.graphs.leetCode695;
import java.util.LinkedList;
import java.util.Queue;


//I already know this is wrong but the first time i tried to do this let code i thought that i needed to check for the patterns of 1 0 1  up down right left.
//I dit it. But at what cost?
public class locura {
    private int connected4Directionally = 0;
    public int maxAreaOfIsland(int[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        boolean[][] visited = new boolean[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j, grid, visited); 
                }
            }
        }
        return connected4Directionally;
    }

    private void bfs(int row, int col, int[][] grid, boolean[][] visited) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { row, col });
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            if (x + 1 < numRows && grid[x + 1][y] != 1) {
                check4Directionally(x + 2, y, grid, visited, queue, numRows, numCols);
            }
            if (x - 1 >= 0 && grid[x - 1][y] != 1) {
                check4Directionally(x - 2, y, grid, visited, queue, numRows, numCols);
            }
            if (y + 1 < numCols && grid[x][y + 1] != 1) {
                check4Directionally(x, y + 2, grid, visited, queue, numRows, numCols);
            }
            if (y - 1 >= 0 && grid[x][y - 1] != 1) {
                check4Directionally(x, y - 2, grid, visited, queue, numRows, numCols);
            }

            // Perform BFS in four directions
            bfsUtil(x + 1, y, grid, visited, queue, numRows, numCols);
            bfsUtil(x - 1, y, grid, visited, queue, numRows, numCols);
            bfsUtil(x, y + 1, grid, visited, queue, numRows, numCols);
            bfsUtil(x, y - 1, grid, visited, queue, numRows, numCols);
        }
    }

    private void bfsUtil(int x, int y, int[][] grid, boolean[][] visited, Queue<int[]> queue,
        int numRows, int numCols) {
        if (x >= 0 && x < numRows && y >= 0 && y < numCols && grid[x][y] == 1 && !visited[x][y]) {
            queue.offer(new int[] { x, y });
            visited[x][y] = true; 
        }
    }

    private void check4Directionally(int x, int y, int[][] grid, boolean[][] visited, Queue<int[]> queue,
        int numRows, int numCols) {
        if (x >= 0 && x < numRows && y >= 0 && y < numCols && grid[x][y] == 1 && !visited[x][y]) {
            if (y + 2 < numCols && grid[x][y + 1] == 0 && grid[x][y + 2] == 1) {
                connected4Directionally++;
            }
            if (y - 2 >= 0 && grid[x][y - 1] == 0 && grid[x][y - 2] == 1) {
                connected4Directionally++;
            }
            if (x + 2 < numRows && grid[x + 1][y] == 0 && grid[x + 2][y] == 1) {
                connected4Directionally++;
            }
            if (x - 2 >= 0 && grid[x - 1][y] == 0 && grid[x - 2][y] == 1) {
                connected4Directionally++;
            }
            visited[x][y] = true;
            queue.offer(new int[]{x, y});
        }
    }

    public static void main(String[] args) {
        locura locura = new locura();
        int[][] grid = {
            {0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };

    System.out.println("Number of occurrences of the pattern '1 0 1': " + locura.maxAreaOfIsland(grid));
    }
}
