package graphproblems;

import java.util.Objects;

public class Edge {
    private Vertex sourceVertex;
    private Vertex destinationVertex;
    private int edgeWeight;

    public Edge(Vertex sourceVertex, Vertex destinationVertex, int edgeWeight) {
        this.sourceVertex = sourceVertex;
        this.destinationVertex = destinationVertex;
        this.edgeWeight = edgeWeight;
    }

    public Vertex getSourceVertex() {
        return sourceVertex;
    }

    public Vertex getDestinationVertex() {
        return destinationVertex;
    }

    public int getEdgeWeight() {
        return edgeWeight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "sourceVertex=" + sourceVertex +
                ", destinationVertex=" + destinationVertex +
                ", edgeWeight=" + edgeWeight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return getEdgeWeight() == edge.getEdgeWeight() &&
                Objects.equals(getSourceVertex(), edge.getSourceVertex()) &&
                Objects.equals(getDestinationVertex(), edge.getDestinationVertex());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSourceVertex(), getDestinationVertex(), getEdgeWeight());
    }
}
