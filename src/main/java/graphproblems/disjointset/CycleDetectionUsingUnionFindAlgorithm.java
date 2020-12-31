package graphproblems.disjointset;

import graphproblems.Edge;
import graphproblems.Graph;
import graphproblems.GraphUtil;
import graphproblems.Node;
import graphproblems.Vertex;

public class CycleDetectionUsingUnionFindAlgorithm {

    public static void main(String[] args) {
        int V = 9;
        Graph graph = new Graph(V);

        graph.addUniDirectionalEdge(0, 1, 0);
        graph.addUniDirectionalEdge(1, 2, 1);
        graph.addUniDirectionalEdge(3,4,2);
        graph.addUniDirectionalEdge(5, 6, 3);
        graph.addUniDirectionalEdge(7, 8, 4);
        graph.addUniDirectionalEdge(2,4, 5);
        graph.addUniDirectionalEdge(2, 5, 6);
        graph.addUniDirectionalEdge(1, 3, 7);
        graph.addUniDirectionalEdge(6, 8, 8);
        graph.addUniDirectionalEdge(5, 7, 9);

        CycleDetectionUsingUnionFindAlgorithm cycleDetection = new CycleDetectionUsingUnionFindAlgorithm();
        System.out.println(cycleDetection.isCyclePresent(graph));
    }

    private boolean isCyclePresent(Graph graph) {
        Vertex[] vertices = graph.getVertices();
        Node[] unionFindData = new Node[graph.getNoOfVertices()];
        GraphUtil.preProcessAndAddDataToDataSetForDisjointSet(graph, unionFindData);
        for (Vertex vertex : vertices) {
            for (Edge edge : vertex.getEdges()) {
                Vertex sourceVertex = edge.getSourceVertex();
                Vertex destinationVertex = edge.getDestinationVertex();
                int sourceVertexRootLabel = UnionFind.find(unionFindData, sourceVertex.getVertexLabel());
                int destinationVertexRootLabel = UnionFind.find(unionFindData, destinationVertex.getVertexLabel());
                // This condition will not satisfy when both vertices forms part of disjoint set ie different connected components
                if (sourceVertexRootLabel == destinationVertexRootLabel) {
                    return true;
                }
                UnionFind.union(unionFindData, sourceVertexRootLabel, destinationVertexRootLabel);
            }
        }
        return false;
    }


}