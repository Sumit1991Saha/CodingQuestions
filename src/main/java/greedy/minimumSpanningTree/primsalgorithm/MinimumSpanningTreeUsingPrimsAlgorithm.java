package greedy.minimumSpanningTree.primsalgorithm;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class MinimumSpanningTreeUsingPrimsAlgorithm {

    public static void main(String[] args) {
        int V = 9;

        Graph graph = new Graph(V);

        MinimumSpanningTreeUsingPrimsAlgorithm mst = new MinimumSpanningTreeUsingPrimsAlgorithm();

        mst.addEdge(graph, 0, 1, 4);
        mst.addEdge(graph, 0, 7, 8);
        mst.addEdge(graph, 1, 2, 3);
        mst.addEdge(graph, 1, 7, 11);
        mst.addEdge(graph, 2, 3, 7);
        mst.addEdge(graph, 2, 8, 2);
        mst.addEdge(graph, 2, 5, 4);
        mst.addEdge(graph, 3, 4, 9);
        mst.addEdge(graph, 3, 5, 14);
        mst.addEdge(graph, 4, 5, 10);
        mst.addEdge(graph, 5, 6, 2);
        mst.addEdge(graph, 6, 7, 1);
        mst.addEdge(graph, 6, 8, 6);
        mst.addEdge(graph, 7, 8, 7);

        //System.out.println(graph.depthFirstTraversal(graph.getVertices()[0]));

        // Method invoked
        mst.findMST(graph);
    }

    private void addEdge(Graph graph, int source, int destination, int edgeWeight) {
        graph.addEdge(source, destination, edgeWeight);
    }

    private void findMST(Graph graph) {
        Queue<Node> minPriorityQueue = new PriorityQueue<>();
        // required for iteration since fetching random data from priority queue is not possible
        // and also used to print the final data of the MST
        Node[] mstData = new Node[graph.getNoOfVertices()];
        Set<Node> visitedSet = new HashSet<>();
        preProcessDataForPriorityQueue(graph, minPriorityQueue, mstData);


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
                    v.setParentNodeLabel(edge.getSourceVertex().getVertexLabel());
                    minPriorityQueue.add(v);
                }
            }
        }

        printMST(mstData);
    }

    private void printMST(Node[] mstData) {
        for (Node node : mstData) {
            System.out.println(node.getNodeLabel() + " - " + node.getParentNodeLabel()
                    + ", weight :- " + node.getVertexKey());
        }
    }

    private void preProcessDataForPriorityQueue(Graph graph, Queue<Node> minPriorityQueue, Node[] tempData) {
        int noOfVertices = graph.getNoOfVertices();
        for (int i = 0; i < noOfVertices; ++i) {
            Vertex[] vertices = graph.getVertices();
            int vertexKey = (i == 0) ? 0 : Integer.MAX_VALUE;
            Node node = new Node(vertices[i].getVertexLabel(), vertexKey, 0);
            tempData[i] = node;
            minPriorityQueue.add(node);
        }
    }
}
