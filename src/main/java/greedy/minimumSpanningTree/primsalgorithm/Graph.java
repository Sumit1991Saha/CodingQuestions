package greedy.minimumSpanningTree.primsalgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Graph {
    private int noOfVertices;
    private Vertex[] vertices;

    public int getNoOfVertices() {
        return noOfVertices;
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public Graph(int noOfVertices) {
        this.noOfVertices = noOfVertices;
        vertices = new Vertex[noOfVertices];
    }

    public void addEdge(int source, int destination, int edgeWeight) {
        Vertex sourceVertex;
        Vertex destinationVertex;
        if (vertices[source] == null) {
            sourceVertex = new Vertex(source, null);
            vertices[source] = sourceVertex;
        } else {
            sourceVertex = vertices[source];
        }

        if (vertices[destination] == null) {
            destinationVertex = new Vertex(destination, null);
            vertices[destination] = destinationVertex;
        } else {
            destinationVertex = vertices[destination];
        }

        Edge edgeSourceToDestination = new Edge(sourceVertex, destinationVertex, edgeWeight);
        Edge edgeDestinationToSource = new Edge(destinationVertex, sourceVertex, edgeWeight);
        sourceVertex.addEdge(edgeSourceToDestination);
        destinationVertex.addEdge(edgeDestinationToSource);
    }

    Set<Integer> depthFirstTraversal(Vertex root) {
        Set<Integer> visited = new LinkedHashSet<>();
        Stack<Vertex> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Vertex vertex = stack.pop();
            if (!visited.contains(vertex.getVertexLabel())) {
                visited.add(vertex.getVertexLabel());
                for (Edge v : vertex.getEdges()) {
                    stack.push(v.getDestinationVertex());
                }
            }
        }
        return visited;
    }
}
