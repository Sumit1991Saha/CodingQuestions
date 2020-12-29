package graphproblems;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Vertex {

    private int vertexLabel;
    private Set<Edge> edges;

    public Vertex(int vertexLabel, Set<Edge> edges) {
        this.vertexLabel = vertexLabel;
        this.edges = edges == null ? new HashSet<>() : edges;
    }

    public int getVertexLabel() {
        return vertexLabel;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void setVertexLabel(int vertexLabel) {
        this.vertexLabel = vertexLabel;
    }

    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex vertex = (Vertex) o;
        return getVertexLabel() == vertex.getVertexLabel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVertexLabel());
    }

}
