package cutthetree;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

    private static List<Node> nodes;

    private static Stack<Node> stack = new Stack<>();

    static class Node {

        public int index;
        public int weight;
        public int childrenWeight = 0;

        public List<Node> children = new ArrayList<>();

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }

    static Set<Integer> visited = new HashSet<>();


    public static int cutTheTree(List<Integer> data, List<List<Integer>> edges) {

        Node node = makeATree(data, edges);
        int totalWeight = data.stream().mapToInt(Integer::intValue).sum();

        visited.add(node.index);
        stack.push(node);
        return calculateChildrenWeightsStack(totalWeight);
    }

    private static int calculateChildrenWeightsStack(int total) {

        int min = total;

        while (!stack.isEmpty()) {

            Node peek = stack.peek();

            boolean allVisited = true;
            for (Node child: peek.children) {

                child.children.remove(peek); //remove back link

                if (!visited.contains(child.index)) {
                    visited.add(child.index);
                    stack.push(child);
                    allVisited = false;
                }
            }
            if (allVisited) {
                stack.pop();

                for (Node child: peek.children) {
                    peek.childrenWeight += child.weight + child.childrenWeight;
                }

                int bottomWeight = peek.weight + peek.childrenWeight;
                int topWeight = total - bottomWeight;
                int diff = Math.abs(bottomWeight - topWeight);
                min = Math.min(diff, min);
            }
        }

        return min;
    }


    private static Node makeATree(List<Integer> data, List<List<Integer>> edges) {

        nodes = IntStream.range(0, data.size())
            .mapToObj(index -> new Node(index+1, data.get(index)))
            .collect(Collectors.toList());

        edges.forEach(edge -> {
            Integer index0 = edge.get(0) - 1;
            Integer index1 = edge.get(1) - 1;
            nodes.get(index0).children.add(nodes.get(index1));
            nodes.get(index1).children.add(nodes.get(index0));
        });

        return nodes.get(0);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> data = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<List<Integer>> edges = new ArrayList<>();

        IntStream.range(0, n - 1).forEach(i -> {
            try {
                edges.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.cutTheTree(data, edges);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
