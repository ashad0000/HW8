/******************************************************************
 *
 *   Abdul Shad / 272
 *
 *   This java file contains the problem solutions of canFinish and
 *   numGroups methods.
 *
 ********************************************************************/

import java.util.*;

class ProblemSolutions {

    public boolean canFinish(int numExams, int[][] prerequisites) {
        int numNodes = numExams;  // # of nodes in graph

        // Build directed graph's adjacency list
        ArrayList<Integer>[] adj = getAdjList(numExams, prerequisites);

        int[] indegree = new int[numNodes]; // Keep track of incoming edges per node

        // Calculate indegree for each node
        for (int node = 0; node < numNodes; node++) {
            for (int neighbor : adj[node]) {
                indegree[neighbor]++; // Increment indegree for each neighbor
            }
        }

        // Queue for nodes with no prerequisites (indegree 0)
        Queue<Integer> queue = new LinkedList<>();
        for (int node = 0; node < numNodes; node++) {
            if (indegree[node] == 0) {
                queue.offer(node); // Add nodes with indegree 0 to the queue
            }
        }

        int completed = 0; // Count of exams that can be completed

        while (!queue.isEmpty()) {
            int current = queue.poll(); // Get node with no prerequisites
            completed++; // One exam can be taken
            for (int neighbor : adj[current]) {
                indegree[neighbor]--; // Remove dependency
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor); // Add neighbor if no remaining prerequisites
                }
            }
        }

        return completed == numNodes; // Return true if all exams can be completed
    }

    private ArrayList<Integer>[] getAdjList(int numNodes, int[][] edges) {
        ArrayList<Integer>[] adj = new ArrayList[numNodes]; // Create an array of ArrayList ADT

        for (int node = 0; node < numNodes; node++) {
            adj[node] = new ArrayList<Integer>(); // Allocate empty ArrayList per node
        }
        for (int[] edge : edges) {
            adj[edge[1]].add(edge[0]); // Reverse the edge direction for correct adjacency
        }
        return adj;
    }

    public int numGroups(int[][] adjMatrix) {
        int numNodes = adjMatrix.length;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int i = 0, j = 0;

        // Converting the Graph Adjacency Matrix to an Adjacency List representation
        for (i = 0; i < numNodes; i++) {
            for (j = 0; j < numNodes; j++) {
                if (adjMatrix[i][j] == 1 && i != j) {
                    graph.putIfAbsent(i, new ArrayList());
                    graph.putIfAbsent(j, new ArrayList());
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        boolean[] visited = new boolean[numNodes]; // Track visited nodes
        int groups = 0; // Count of groups

        // Visit all nodes
        for (int node = 0; node < numNodes; node++) {
            if (!visited[node]) {
                dfs(node, graph, visited); // Perform DFS on unvisited node
                groups++; // Increment group count after each DFS
            }
        }

        return groups;
    }

    // Helper method for DFS traversal
    private void dfs(int node, Map<Integer, List<Integer>> graph, boolean[] visited) {
        visited[node] = true; // Mark current node as visited
        if (graph.containsKey(node)) {
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    dfs(neighbor, graph, visited); // Recursively visit neighbors
                }
            }
        }
    }
}
