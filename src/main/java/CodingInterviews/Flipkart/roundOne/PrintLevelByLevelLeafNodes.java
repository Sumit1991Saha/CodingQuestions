package CodingInterviews.Flipkart.roundOne;

import java.util.*;

/*
Given a N-array tree, print the leaf nodes first, then remove the leaf nodes and print the next set of leaf nodes and so on till root node.
                                        1
                                 /      |         \
                              2        3           4
                           /.  \       |          /. \
                       5.        6.     8      9      10
                    /                                   \
                 7                                       11
Output should be :-
7 6 8 9 11
5 3 10
2 4
1

 */
public class PrintLevelByLevelLeafNodes {

    public static void main(String[] args) {
        PrintLevelByLevelLeafNodes printLevelByLevelLeafNodes = new PrintLevelByLevelLeafNodes();
        Node root = printLevelByLevelLeafNodes.constructNArrayTree();
        printLevelByLevelLeafNodes.printTreeContentLevelOrder(root);
        printLevelByLevelLeafNodes.assignHeightOfSubtreeAtNode(root);
        printLevelByLevelLeafNodes.printLevelByLevelLeafNodes(root);
    }


    private Node constructNArrayTree() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);

        node1.addChild(node2);
        node1.addChild(node3);
        node1.addChild(node4);

        node2.addChild(node5);
        node2.addChild(node6);

        node3.addChild(node8);

        node4.addChild(node9);
        node4.addChild(node10);

        node5.addChild(node7);
        node5.addChild(node12);

        node10.addChild(node11);

        return node1;

    }

    private void printTreeContentLevelOrder(Node root) {
        List<Node> queue = new LinkedList<>();
        queue.add(root);
        addDelimiter(queue);
        while (!queue.isEmpty()) {
            Node node = queue.remove(0);
            if (node.value != -1) {
                System.out.print(node.value + " ");
                if (node.children != null) { // condition to check for leaf nodes
                    queue.addAll(node.children);
                }
                if (queue.get(0).value == -1) {
                    addDelimiter(queue);
                }
            } else {
                System.out.println("");
            }
        }
    }

    private void assignHeightOfSubtreeAtNode(Node node) {
        if (node == null) {
            return;
        }
        if (node.children == null) {
            node.heightOfSubTree = 0;
        } else {
            int height = 0;
            for (Node child : node.children) {
                assignHeightOfSubtreeAtNode(child);
                height = Math.max(height, child.heightOfSubTree);
            }
            node.heightOfSubTree = height + 1;
        }
    }

    private void addDelimiter(List<Node> queue) {
        queue.add(new Node(-1)); // -1 acts as a delimiter
    }

    private void printLevelByLevelLeafNodes(Node root) {
        Map<Integer, List<Node>> levelWiseLeafNodes = new TreeMap<>();

        List<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove(0);
            if (levelWiseLeafNodes.containsKey(node.heightOfSubTree)) {
                levelWiseLeafNodes.get(node.heightOfSubTree).add(node);
            } else {
                List<Node> nodes = new LinkedList<>();
                nodes.add(node);
                levelWiseLeafNodes.put(node.heightOfSubTree, nodes);
            }
            if (node.children != null) { // condition to check for leaf nodes
                queue.addAll(node.children);
            }
        }
        for (Map.Entry<Integer, List<Node>> entry : levelWiseLeafNodes.entrySet()) {
            List<Node> nodes = entry.getValue();
            for(Node node : nodes) {
                System.out.print(node.value + " ");
            }
            System.out.println("");
        }
    }

    static class Node {
        int value;
        int heightOfSubTree;
        List<Node> children;

        Node(int value) {
            this.value = value;
        }

        void addChild(Node node) {
            if (children == null) {
                children = new LinkedList<>();
            }
            children.add(node);
        }
    }
}
