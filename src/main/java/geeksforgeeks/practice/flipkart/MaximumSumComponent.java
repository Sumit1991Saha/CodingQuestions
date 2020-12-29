package geeksforgeeks.practice.flipkart;

import graphproblems.Edge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaximumSumComponent {

    public static void main(String[] args) {
        int[] Values = {10, 25, 5, 15, 5, 20, 0};
        int[][] Edges ={ {1,2} ,{3,4}, {4,5} ,{6,7} };
        System.out.println(solve(Values.length, Edges.length, Values, Edges));
    }

    static long solve(int V, int E, int[] Values, int[][] Edges) {
        int maxSum = 0;

        System.out.println(Arrays.toString(Values));
        System.out.println(Arrays.deepToString(Edges));
        Set<Integer> uniqueVertex = new HashSet<>();
        for (int i = 0; i < E; ++i) {
            for (int j = 0; j < Edges[0].length; ++j) {
                uniqueVertex.add(Edges[i][j]);
            }
        }

        Map<Integer, Set<Integer>> connectedComponents = new HashMap<>();
        uniqueVertex.stream()
                .forEach(val -> {
                    Set<Integer> values = new HashSet<Integer>() {{
                        add(val);
                    }};
                    connectedComponents.put(val, values);
                });

        for (int i = 0; i < E; ++i) {
            int source = Edges[i][0];
            int destination = Edges[i][1];
            Set<Integer> sourceSet = connectedComponents.get(source);
            Set<Integer> destinationSet = connectedComponents.get(destination);
            if (destinationSet != null) {
                if (sourceSet != null) {
                    sourceSet.addAll(destinationSet);
                } else {
                    for (Set<Integer> connectedComponent : connectedComponents.values()) {
                        if (connectedComponent.contains(source)) {
                            connectedComponent.addAll(destinationSet);
                            break;
                        }
                    }
                }
                connectedComponents.remove(destination);
            }
        }

        for(Set<Integer> connectedComponent : connectedComponents.values()) {
            int connectedComponentSum = 0;
            for (int vertex : connectedComponent) {
                connectedComponentSum += Values[vertex-1];
            }
            if (connectedComponentSum > maxSum) {
                maxSum = connectedComponentSum;
            }
        }

        return maxSum;
    }
}
