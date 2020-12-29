package graphproblems.minimumSpanningTree;

import java.util.Objects;

public class Node implements Comparable<Node>{

    private int nodeLabel;
    private int vertexKey;
    private int parentNodeLabel;

    public Node(int nodeLabel, int vertexKey, int parentNodeLabel) {
        this.nodeLabel = nodeLabel;
        this.vertexKey = vertexKey;
        this.parentNodeLabel = parentNodeLabel;
    }

    public int getNodeLabel() {
        return nodeLabel;
    }

    public void setNodeLabel(int nodeLabel) {
        this.nodeLabel = nodeLabel;
    }

    public int getVertexKey() {
        return vertexKey;
    }

    public void setVertexKey(int vertexKey) {
        this.vertexKey = vertexKey;
    }

    public int getParentNodeLabel() {
        return parentNodeLabel;
    }

    public void setParentNodeLabel(int parentNodeLabel) {
        this.parentNodeLabel = parentNodeLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node that = (Node) o;
        return getNodeLabel() == that.getNodeLabel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNodeLabel());
    }

    @Override
    public int compareTo(Node o) {
        return this.vertexKey - o.vertexKey;
    }
}
