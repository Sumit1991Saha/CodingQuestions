package graphproblems;

import java.util.Queue;

public class GraphUtil {
    public static void preProcessAndAddDataToPriorityQueue(Graph graph, Queue<Node> minPriorityQueue, Node[] tempData) {
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
