/******************************************************************
 *
 *   Abdul Shad / 272
 *
 *   Note, additional comments provided throughout this source code
 *   is for educational purposes
 *
 ********************************************************************/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    int numVertices;                  // vertices in graph
    LinkedList<Integer>[] adjListArr; // Adjacency list
    List<Integer> vertexValues;       // vertex values

    // Constructor
    public Graph(int numV) {
        numVertices = numV;
        adjListArr = new LinkedList[numVertices];
        vertexValues = new ArrayList<>(numVertices);

        for (int i = 0; i < numVertices; i++) {
            adjListArr[i] = new LinkedList<>();
            vertexValues.add(0);
        }
    }

    public void setValue(int vertexIndex, int value) {
        if (vertexIndex >= 0 && vertexIndex < numVertices) {
            vertexValues.set(vertexIndex, value);
        } else {
            throw new IllegalArgumentException(
                    "Invalid vertex index: " + vertexIndex);
        }
    }

    public void addEdge(int src, int dest) {
        adjListArr[src].add(dest);
    }

    public void printGraph() {
        System.out.println(
                "\nAdjacency Matrix Representation:\n");
        int[][] matrix = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (Integer dest : adjListArr[i]) {
                matrix[i][dest] = 1;
            }
        }

        System.out.print("  ");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < numVertices; j++) {
                if (matrix[i][j] == 1) {
                    System.out.print("| ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    /**
     * method findRoot
     *
     * This method returns the value of the root vertex, where root is defined in
     * this case as a node that has no incoming edges. If no root vertex is found
     * and/or more than one root vertex, then return -1.
     */

    public int findRoot() {
        int[] incoming = new int[numVertices]; // Array to track number of incoming edges per node

        // Count incoming edges for each vertex
        for (int src = 0; src < numVertices; src++) {
            for (int dest : adjListArr[src]) {
                incoming[dest]++; // Increment incoming edge count for destination vertex
            }
        }

        int rootIndex = -1; // To store the index of root if found

        // Check for vertices with zero incoming edges
        for (int v = 0; v < numVertices; v++) {
            if (incoming[v] == 0) {
                if (rootIndex != -1) {
                    return -1; // More than one root found, so return -1
                }
                rootIndex = v; // Found potential root
            }
        }

        if (rootIndex == -1) {
            return -1; // No root found
        } else {
            return vertexValues.get(rootIndex); // Return the value of the root vertex
        }
    }
}
