package graphproblems.disjointset;

import graphproblems.Node;

/**
 *  Union-Find algorithm.
 *  The union() and find() are naive and the worst case time complexity is linear.
 *  The trees created to represent subsets can be skewed and can become like a linked list.
 *
 *  unionOptimized() does 2 things :-
 *  1. Flatten the skewed parent mapping by assigning the root of the node as the parent of successive node being attached to the tree.
 *  2. Checking the subtree first ie which sub tree should get attached to which subtree using a field called rank of the tree ie size of the tree.
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
        Node nodeForDestinationVertex = unionFindData[destinationVertexLabel];
        nodeForDestinationVertex.setParentNodeLabel(sourceVertexLabel);
    }

    static void unionOptimized(Node[] unionFindData, int sourceVertexLabel, int destinationVertexLabel) {
        int sourceVertexRootLabel = find(unionFindData, sourceVertexLabel);
        int destinationVertexRootLabel = find(unionFindData, destinationVertexLabel);
        Node nodeForSourceVertexRoot = unionFindData[sourceVertexRootLabel];
        Node nodeForDestinationVertexRoot = unionFindData[destinationVertexRootLabel];

        int sourceVertexKey = nodeForSourceVertexRoot.getVertexKey();
        int destinationVertexKey = nodeForDestinationVertexRoot.getVertexKey();

        // so here we check if its value is higher then its rank is higher and it becomes tha parent of lower rank node.
        if (sourceVertexKey >= destinationVertexKey) {
            nodeForDestinationVertexRoot.setParentNodeLabel(sourceVertexRootLabel);
            nodeForSourceVertexRoot.setVertexKey(sourceVertexKey + destinationVertexKey);
        } else {
            nodeForSourceVertexRoot.setParentNodeLabel(destinationVertexRootLabel);
            nodeForDestinationVertexRoot.setVertexKey(sourceVertexKey + destinationVertexKey);
        }
    }
}
