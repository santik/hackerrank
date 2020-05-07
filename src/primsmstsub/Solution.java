package primsmstsub;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    static class Path {
        public int from;
        public int to;

        public int getWeight() {
            return weight;
        }

        public int weight;

        public Path(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static int prims(int n, int[][] edges, int start) {

        Map<Integer, List<Path>> graph = buildGraph(edges);

        Set<Integer> visited = new HashSet<>();

        Integer current = start;
        visited.add(current);

        int result = 0;

        List<Path> possiblePaths = new ArrayList<>(graph.get(current));

        while (visited.size() != n) {

            List<Path> paths = possiblePaths.stream().filter(
                    path -> !visited.contains(path.to)).collect(Collectors.toList());


            Path path = paths.stream().min(Comparator.comparing(Path::getWeight)).orElse(new Path(0,0,0));

            current = path.to;
            visited.add(current);
            result += path.weight;
            possiblePaths.addAll(graph.get(current));
        }

        return result;
    }

    private static Map<Integer, List<Path>> buildGraph(int[][] edges) {

        Map<Integer, List<Path>> directions = new HashMap<>();

        for (int[] edge : edges) {
            int start = edge[0];
            int finish = edge[1];
            int weight = edge[2];

            List<Path> nodeStartDirections = directions.computeIfAbsent(start, k -> new ArrayList<>());
            List<Path> nodeFinishDirections = directions.computeIfAbsent(finish, k -> new ArrayList<>());

            nodeStartDirections.add(new Path(start, finish, weight));
            nodeFinishDirections.add(new Path(finish, start, weight));
        }

        return directions;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] edges = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] edgesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int edgesItem = Integer.parseInt(edgesRowItems[j]);
                edges[i][j] = edgesItem;
            }
        }

        int start = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = prims(n, edges, start);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
