package graphproblems.greedy.minimumSpanningTree;

import graphproblems.Edge;
import graphproblems.Graph;
import graphproblems.GraphUtil;
import graphproblems.Node;
import graphproblems.Vertex;
import graphproblems.disjointset.UnionFindAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class KruskalsAlgorithm {

    public static void main(String[] args) {
        int V = 9;
        Graph graph = new Graph(V);

        // Edges with parallel edges
        graph.addBiDirectionalEdge(0, 1, 40);
        graph.addBiDirectionalEdge(0, 1, 10);
        graph.addBiDirectionalEdge(0, 1, 4);

        // self loop
        graph.addBiDirectionalEdge(1, 1, 5);
        graph.addBiDirectionalEdge(2, 2, 6);

        graph.addBiDirectionalEdge(0, 1, 4);
        graph.addBiDirectionalEdge(0, 7, 8);
        graph.addBiDirectionalEdge(1, 2, 3);
        graph.addBiDirectionalEdge(1, 7, 11);
        graph.addBiDirectionalEdge(2, 3, 7);
        graph.addBiDirectionalEdge(2, 8, 2);
        graph.addBiDirectionalEdge(2, 5, 4);
        graph.addBiDirectionalEdge(3, 4, 9);
        graph.addBiDirectionalEdge(3, 5, 14);
        graph.addBiDirectionalEdge(4, 5, 10);
        graph.addBiDirectionalEdge(5, 6, 2);
        graph.addBiDirectionalEdge(6, 7, 1);
        graph.addBiDirectionalEdge(6, 8, 6);
        graph.addBiDirectionalEdge(7, 8, 7);

        //System.out.println(graph.depthFirstTraversal(graph.getVertices()[0]));

        KruskalsAlgorithm mst = new KruskalsAlgorithm();
        Node[] mstData = mst.findMST(graph);
        GraphUtil.printMST(mstData);
    }

    private Node[] findMST(Graph graph) {
        Vertex[] vertices = graph.getVertices();
        UnionFindAlgorithm unionFindAlgorithm = new UnionFindAlgorithm(graph);

        List<Edge> edgesInMST = new ArrayList<>();
        Set<Vertex> visitedVertex = new HashSet<>();
        List<Edge> edgesList = new ArrayList<>();
        for (Vertex vertex : vertices) {
            Set<Edge> edgesOfVertex = vertex.getEdges();
            List<Edge> trimmedEdgesWithoutSelfLoops = removeSelfLoops(edgesOfVertex);
            List<Edge> edgesWithoutParallelEdges = removeParallelEdges(trimmedEdgesWithoutSelfLoops);
            edgesList.addAll(edgesWithoutParallelEdges);
        }

        // At this stage, self loop edges and parallel edges are removed.
        edgesList.sort(new EdgeComparatorByWeight());

        edgesList.forEach(edge -> {
            Vertex sourceVertex = edge.getSourceVertex();
            Vertex destinationVertex = edge.getDestinationVertex();

            // Applying union and find algorithm
            int sourceVertexRootLabel = unionFindAlgorithm.find(sourceVertex.getVertexLabel());
            int destinationVertexRootLabel = unionFindAlgorithm.find(destinationVertex.getVertexLabel());
            // If belonging to different connected component then merge both set of components.
            if (sourceVertexRootLabel != destinationVertexRootLabel) {
                edgesInMST.add(edge);
                unionFindAlgorithm.unionOptimized(sourceVertex.getVertexLabel(), destinationVertex.getVertexLabel());
            }
        });

        edgesInMST.sort(new EdgeComparatorBySourceVertex());
        Node[] finalMstData = new Node[edgesInMST.size() + 1];
        int index = 0;
        for (Edge edge : edgesInMST) {
            Vertex sourceVertex = edge.getSourceVertex();
            Vertex destinationVertex = edge.getDestinationVertex();
            if (index == 0) {
                Node nodeDataForSource = new Node(sourceVertex.getVertexLabel(), 0, -1);
                finalMstData[index++] = nodeDataForSource;
            }
            Node nodeDataForDestination = new Node(destinationVertex.getVertexLabel(), edge.getEdgeWeight(), sourceVertex.getVertexLabel());
            finalMstData[index++] = nodeDataForDestination;
        }

        return finalMstData;
    }

    private List<Edge> removeSelfLoops(Collection<Edge> edges) {
        List<Edge> trimmedEdgesWithoutSelfLoops = edges.stream()
                .filter(edge -> !edge.getDestinationVertex().equals(edge.getSourceVertex()))
                .collect(Collectors.toList());
        return trimmedEdgesWithoutSelfLoops;
    }

    private List<Edge> removeParallelEdges(List<Edge> edgesListWithParallelEdges) {
        //this list is already sorted by edge weight in case edges are parallel edges.
        edgesListWithParallelEdges.sort(new EdgeComparatorByDestinationVertex());
        List<Edge> edgesListWithoutParallelEdges = new ArrayList<>();
        for (int i = 0; i < edgesListWithParallelEdges.size(); ++i) {
            int j = i + 1;
            Edge edgeWithMinimumEdgeWeight = edgesListWithParallelEdges.get(i);
            edgesListWithoutParallelEdges.add(edgeWithMinimumEdgeWeight);
            while (j < edgesListWithParallelEdges.size()) {
                Edge parallelEdge = edgesListWithParallelEdges.get(j);
                if (edgeWithMinimumEdgeWeight.getDestinationVertex() != parallelEdge.getDestinationVertex()) {
                    break;
                }
                ++j;
            }
            i = j - 1;
        }
        return edgesListWithoutParallelEdges;
    }

    private static class EdgeComparatorBySourceVertex implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.getSourceVertex().getVertexLabel() - o2.getSourceVertex().getVertexLabel();
        }
    }

    private static class EdgeComparatorByWeight implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.getEdgeWeight() - o2.getEdgeWeight();
        }
    }

    // For a vertex , source remains the same only destination changes.
    private static class EdgeComparatorByDestinationVertex implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            int compareValue;
            if (o1.getDestinationVertex().getVertexLabel() == o2.getDestinationVertex().getVertexLabel()) {
                compareValue = o1.getEdgeWeight() - o2.getEdgeWeight();
            } else {
                compareValue = o1.getDestinationVertex().getVertexLabel() - o2.getDestinationVertex().getVertexLabel();
            }
            return compareValue;
        }
    }
}
