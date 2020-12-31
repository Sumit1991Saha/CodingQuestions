package graphproblems.disjointset;

import graphproblems.Node;
import graphproblems.Vertex;

/**
 *  Union-Find algorithm.
 *  The union() and find() are naive and the worst case time complexity is linear.
 *  The trees created to represent subsets can be skewed and can become like a linked list.
 */
public class UnionFind {
    // To find Root node label
    static int find(Node[] unionFindData, int vertexLabel) {
        Node nodeForVertex = unionFindData[vertexLabel];
        if (nodeForVertex.getParentNodeLabel() == -1) {
            return nodeForVertex.getNodeLabel();
        }
        return find(unionFindData, nodeForVertex.getParentNodeLabel());
    }

    static void union(Node[] unionFindData, int sourceVertexLabel, int destinationVertexLabel) {
        int sourceVertexRootLabel = find(unionFindData, sourceVertexLabel);
        int destinationVertexRootLabel = find(unionFindData, destinationVertexLabel);
        Node nodeForDestinationVertex = unionFindData[destinationVertexRootLabel];
        nodeForDestinationVertex.setParentNodeLabel(sourceVertexRootLabel);
    }
}
