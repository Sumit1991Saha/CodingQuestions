package graphproblems;

import java.util.LinkedHashSet;
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

    public void addBiDirectionalEdge(int source, int destination, int edgeWeight) {
        Vertex sourceVertex = getOrCreateVertex(source);
        Vertex destinationVertex = getOrCreateVertex(destination);

        Edge edgeSourceToDestination = new Edge(sourceVertex, destinationVertex, edgeWeight);
        sourceVertex.addEdge(edgeSourceToDestination);

        Edge edgeDestinationToSource = new Edge(destinationVertex, sourceVertex, edgeWeight);
        destinationVertex.addEdge(edgeDestinationToSource);
    }

    public void addUniDirectionalEdge(int source, int destination, int edgeWeight) {
        Vertex sourceVertex = getOrCreateVertex(source);
        Vertex destinationVertex = getOrCreateVertex(destination);

        Edge edgeSourceToDestination = new Edge(sourceVertex, destinationVertex, edgeWeight);
        sourceVertex.addEdge(edgeSourceToDestination);
    }

    private Vertex getOrCreateVertex(int vertexLabel) {
        Vertex vertex;
        if (vertices[vertexLabel] == null) {
            vertex = new Vertex(vertexLabel, null);
            vertices[vertexLabel] = vertex;
        } else {
            vertex = vertices[vertexLabel];
        }
        return vertex;
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
