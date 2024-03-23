package graph.matrix;


import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class matrix {
    public int[][] adjacencyMatrix;
    public int numVertices;

    public matrix(int numVertices) {
        this.numVertices = numVertices;
        adjacencyMatrix = new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
    }


    public class pair {
        public int first;
        public int second;

        public pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public Boolean isValid(Boolean vis[][], int row, int col) {
        if (row < 0 || col < 0 ||
                row >= numVertices || col >= numVertices)
            return false;
    
        if (vis[row][col])
            return false;
        return true;
    }
    public void depthFirstSearch(int row, int column, int[][] grid, Boolean[][] vis) {
        Stack<pair> stack = new Stack<pair>();
        stack.push(new pair(row, column));
    
        while (!stack.isEmpty()) {
            pair curr = stack.pop();
            row = curr.first;
            column = curr.second;
    
            if (!isValid(vis, row, column))
                continue;
            vis[row][column] = true;
            System.out.println(row + " " + column);
    
            for (int i = 0; i < numVertices; i++) {
                if (grid[row][i] == 1 && !vis[row][i]) {
                    stack.push(new pair(i, column));
                }
            }
        }
    }

    public void breadFirstSearch() {
        Boolean[] visited = new Boolean[numVertices];
        Arrays.fill(visited, false);
    
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                System.out.println("Connected component from vertex " + i + ":");
                bfs(i, visited);
                System.out.println();
            }
        }
    }
    public void bfs(int startVertex, Boolean[] visited){
        List<Integer> queue = new ArrayList<>();
        queue.add(startVertex);
        
        visited[startVertex] = true;

        while (!queue.isEmpty()) {
            int vis = queue.remove(0);
            System.out.print(vis + " ");
            for (int i = 0; i < numVertices; i++) {
                if (adjacencyMatrix[vis][i] == 1 && (!visited[i]))
                {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    public void addEdge(int u, int v) {
        adjacencyMatrix[u][v] = 1;
        adjacencyMatrix[v][u] = 1;
    }

    public void removeEdge(int u, int v) {
        adjacencyMatrix[u][v] = 0;
        adjacencyMatrix[v][u] = 0;
    }

    public void addNode(int u) {
        int x = u + numVertices;
        int[][] newMatrix = new int[x][x];

        for (int i = 0; i < numVertices; i++) {
            System.arraycopy(adjacencyMatrix[i], 0, newMatrix[i], 0, numVertices);
        }

        numVertices = x;
        adjacencyMatrix = newMatrix;
    }

    public void removeNode(int u) {

        for (int i = 0; i < numVertices; i++) {
            removeEdge(u, i);
        }

        for (int i = u; i < numVertices - 1; i++) {
            adjacencyMatrix[i] = adjacencyMatrix[i + 1].clone();
        }
        for (int i = 0; i < numVertices; i++) {
            adjacencyMatrix[i][u] = adjacencyMatrix[i][numVertices - 1];
        }
        numVertices--;
    }

    public void printAdjacencyMatrix() {
        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        matrix matrix = new matrix(5);
        matrix.addEdge(1, 2);
        matrix.addEdge(0, 1);
        matrix.addEdge(1, 3);
        matrix.addEdge(1, 4);
        matrix.printAdjacencyMatrix();
        matrix.removeNode(4);
        matrix.printAdjacencyMatrix();
        matrix.addNode(4);
        matrix.addEdge(3, 2);
        matrix.addEdge(5, 1);
        matrix.addEdge(6, 3);
        matrix.addEdge(4, 2);
        matrix.addEdge(3, 4);
        matrix.addEdge(5, 2);
        matrix.addEdge(6, 1);
        matrix.addEdge(1, 5);
        matrix.addEdge(6, 4);
        matrix.addEdge(7, 0);
        matrix.printAdjacencyMatrix();

        Boolean[][] vis = new Boolean[matrix.numVertices][matrix.numVertices];
        for (int i = 0; i < matrix.numVertices; i++) {
            for (int j = 0; j < matrix.numVertices; j++) {
                vis[i][j] = false; 
            }
        }
        System.out.println("depthfirstsearch:");
        matrix.depthFirstSearch(1, 0, matrix.adjacencyMatrix, vis);
        System.out.println("breadFirstSearch:");
        matrix.breadFirstSearch();
    }
}