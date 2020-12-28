package greedy.minimumSpanningTree.primsalgorithm;

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
}
