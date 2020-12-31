package graphproblems.greedy.minimumSpanningTree;

import graphproblems.Edge;
import graphproblems.Graph;
import graphproblems.GraphUtil;
import graphproblems.Node;
import graphproblems.Vertex;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class PrimsAlgorithm {

    public static void main(String[] args) {
        int V = 9;
        Graph graph = new Graph(V);

        graph.addBiDirectionalEdge(0, 1, 4);
        graph.addBiDirectionalEdge(0, 7, 8);
        graph.addBiDirectionalEdge( 1, 2, 3);
        graph.addBiDirectionalEdge( 1, 7, 11);
        graph.addBiDirectionalEdge( 2, 3, 7);
        graph.addBiDirectionalEdge( 2, 8, 2);
        graph.addBiDirectionalEdge( 2, 5, 4);
        graph.addBiDirectionalEdge( 3, 4, 9);
        graph.addBiDirectionalEdge( 3, 5, 14);
        graph.addBiDirectionalEdge( 4, 5, 10);
        graph.addBiDirectionalEdge( 5, 6, 2);
        graph.addBiDirectionalEdge( 6, 7, 1);
        graph.addBiDirectionalEdge( 6, 8, 6);
        graph.addBiDirectionalEdge( 7, 8, 7);

        System.out.println(graph.depthFirstTraversal(graph.getVertices()[0]));

        PrimsAlgorithm mst = new PrimsAlgorithm();
        Node[] mstData = mst.findMST(graph);
        GraphUtil.printMST(mstData);
    }

    private Node[] findMST(Graph graph) {
        Queue<Node> minPriorityQueue = new PriorityQueue<>();
        // required for iteration since fetching random data from priority queue is not possible
        // and also used to print the final data of the MST
        Node[] mstData = new Node[graph.getNoOfVertices()];
        Set<Node> visitedSet = new HashSet<>();
        GraphUtil.preProcessAndAddDataToPriorityQueue(graph, minPriorityQueue, mstData);

        while (!minPriorityQueue.isEmpty()) {
            Node u = minPriorityQueue.poll();
            visitedSet.add(u);

            int indexOfVertexU = u.getNodeLabel();
            Vertex vertexAtU = graph.getVertices()[indexOfVertexU];
            for (Edge edge : vertexAtU.getEdges()) {
                Node v = mstData[edge.getDestinationVertex().getVertexLabel()];
                if (!visitedSet.contains(v) && edge.getEdgeWeight() < v.getVertexKey()) {
                    minPriorityQueue.remove(v);
                    v.setVertexKey(edge.getEdgeWeight());
                    v.setParentNodeLabel(u.getNodeLabel());
                    minPriorityQueue.add(v);
                }
            }
        }

        return mstData;
    }
}
