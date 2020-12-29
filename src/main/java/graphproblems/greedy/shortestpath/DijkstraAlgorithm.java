package graphproblems.greedy.shortestpath;

import graphproblems.Edge;
import graphproblems.Graph;
import graphproblems.GraphUtil;
import graphproblems.Node;
import graphproblems.Vertex;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        int V = 9;
        Graph graph = new Graph(V);

        graph.addBiDirectionalEdge(0, 1, 4);
        graph.addBiDirectionalEdge(0, 7, 8);
        graph.addBiDirectionalEdge( 1, 2, 8);
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

        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
        Node[] shortestPath = dijkstra.findShortestPath(graph);
        dijkstra.printShortestPath(shortestPath);
    }

    private Node[] findShortestPath(Graph graph) {
        Queue<Node> minPriorityQueue = new PriorityQueue<>();
        // required for iteration since fetching random data from priority queue is not possible
        // and also used to print the final data of the shortest path
        Node[] dijkstraData = new Node[graph.getNoOfVertices()];
        Set<Node> visitedSet = new HashSet<>();
        GraphUtil.preProcessAndAddDataToPriorityQueue(graph, minPriorityQueue, dijkstraData);

        while (!minPriorityQueue.isEmpty()) {
            Node u = minPriorityQueue.poll();
            visitedSet.add(u);
            int indexOfVertexU = u.getNodeLabel();
            Vertex vertexAtU = graph.getVertices()[indexOfVertexU];
            for (Edge edge : vertexAtU.getEdges()) {
                Node v = dijkstraData[edge.getDestinationVertex().getVertexLabel()];
                int distanceFromUForGivenEdge = u.getVertexKey() + edge.getEdgeWeight();
                if (!visitedSet.contains(v) && (distanceFromUForGivenEdge < v.getVertexKey())) {
                    minPriorityQueue.remove(v);
                    v.setVertexKey(distanceFromUForGivenEdge);
                    v.setParentNodeLabel(u.getNodeLabel());
                    minPriorityQueue.add(v);
                }
            }
        }

        return dijkstraData;
    }

    private void printShortestPath(Node[] dijkstraData) {
        System.out.println("Child - Parent :- Distance from - " + dijkstraData[0].getNodeLabel());
        for (Node node : dijkstraData) {
            System.out.println(node.getNodeLabel() + " - " + node.getParentNodeLabel() + " :- " + node.getVertexKey());
        }
    }
}
