package graphproblems;

import java.util.Queue;

public class GraphUtil {
    public static void preProcessAndAddDataToPriorityQueue(Graph graph, Queue<Node> minPriorityQueue, Node[] dataSet) {
        int noOfVertices = graph.getNoOfVertices();
        Vertex[] vertices = graph.getVertices();
        for (int i = 0; i < noOfVertices; ++i) {
            int vertexKey = (i == 0) ? 0 : Integer.MAX_VALUE;
            Node node = new Node(vertices[i].getVertexLabel(), vertexKey, 0);
            dataSet[i] = node;
            minPriorityQueue.add(node);
        }
    }

    public static void preProcessAndAddDataToDataSet(Graph graph, Node[] dataSet) {
        Vertex[] vertices = graph.getVertices();
        int noOfVertices = graph.getNoOfVertices();
        for (int i = 0; i < noOfVertices; ++i) {
            int vertexLabel = vertices[i].getVertexLabel();
            // Adding -1 as parent label so as to imply that, that node is the root node to itself.
            // Not using vertex key in this method.
            Node node = new Node(vertexLabel, 0, -1);
            dataSet[i] = node;
        }
    }
}
