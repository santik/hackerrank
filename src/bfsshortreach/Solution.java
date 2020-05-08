package bfsshortreach;

import java.io.*;
import java.util.*;

public class Solution {

    public static final int weight = 6;

    static class Node {
        public int id;
        public List<Node> children = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }
    }

    static Integer[] bfs(int n, int m, int[][] edges, int s) {

        List<Node> nodes = buildTree(n, edges);
        Map<Integer, Integer> levels = findPath(s, nodes);

        List<Integer> answer = new ArrayList<>();
        nodes.forEach(node -> {
            if (node.id != s) {
                Integer level = levels.get(node.id);
                int path = -1;
                if (level != null) {
                    path = level * weight;
                }
                answer.add(path);
            }
        });

        return answer.toArray(new Integer[0]);
    }

    private static Map<Integer, Integer> findPath(int start, List<Node> nodes) {

        Set<Integer> added = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> levels = new HashMap<>();

        added.add(start);
        queue.add(start);
        levels.put(start, 0);

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            Node current = nodes.get(poll - 1);

            current.children.forEach(node -> {
                if (!added.contains(node.id)) {
                    queue.add(node.id);
                    added.add(node.id);
                    levels.put(node.id, levels.get(current.id) + 1);
                }
            });
        }

        return levels;
    }

    private static List<Node> buildTree(int n, int[][] edges) {

        List<Node> nodes = new ArrayList<>();

        for (int i = 1; i<=n; i++) {
            nodes.add(new Node(i));
        }

        for (int[] edge: edges) {
            int start = edge[0];
            int finish = edge[1];

            nodes.get(start - 1).children.add(nodes.get(finish - 1));
            nodes.get(finish - 1).children.add(nodes.get(start - 1));
        }

        return nodes;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));


        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            Integer[] result = bfs(n, m, edges, s);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
