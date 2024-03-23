package graph.list;

import java.util.Stack;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class list {
    public int numVertices;
    List<List<Integer>> adjList;

    public list(int numVertices) {
        this.numVertices = numVertices;
        this.adjList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void depthFirstSearch(int u) {
        Set<Integer> visitedVertices = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(u);

        while (!stack.isEmpty()) {
            int x = stack.pop();
            if (!visitedVertices.contains(x)) {
                visitedVertices.add(x);
                System.out.print(x + " ");
                for (int neighbor : adjList.get(x)) {
                    if (!visitedVertices.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        System.out.println();
    }

    public void breadFirstSearch(int u) {
        Set<Integer> visitedVertices = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(u);

        while (!queue.isEmpty()) {
            int x = queue.poll();
            if (!visitedVertices.contains(x)) {
                visitedVertices.add(x);
                System.out.print(x + " ");
                for (int neighbor : adjList.get(x)) {
                    if (!visitedVertices.contains(neighbor)) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        System.out.println();
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public void removeEdge(int u, int v) {
        List<Integer> adjU = adjList.get(u);
        List<Integer> adjV = adjList.get(v);
        int index = adjU.indexOf(v);
        if (index != -1) {
            adjU.remove(index);
        }
        int indexV = adjV.indexOf(u);
        if (indexV != -1) {
            adjV.remove(indexV);
        }
    }

    public void addNode(int u) {
        for (int i = 0; i < u; i++) {
            adjList.add(new ArrayList<>());
            numVertices++;
        }
    }

    public void removeNode(int u) {
        for (int v = 0; v < numVertices; v++) {
            removeEdge(u, v);
        }
        adjList.remove(u);
        numVertices--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numVertices; i++) {
            sb.append(i).append(": ").append(adjList.get(i)).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        list list = new list(2);
        list.addEdge(0, 1);
        list.addNode(5);
        list.addEdge(2, 1);
        list.addEdge(3, 2);
        list.addEdge(4, 3);
        list.addEdge(5, 0);
        list.addEdge(5, 1);
        list.depthFirstSearch(0);
        list.breadFirstSearch(0);
        System.out.println(list);
    }
}
