package gena;

import java.util.*;
import java.util.stream.IntStream;

public class Solution {


    static class State implements Cloneable {
        Integer level;
        List<Stack<Integer>> rods = Arrays.asList(new Stack<>(), new Stack<>(), new Stack<>(), new Stack<>());

        @Override
        protected State clone() {
            State state = new State();
            state.level = level;
            IntStream.range(0, rods.size()).forEach(idx -> {
                rods.get(idx).forEach(c -> {
                    state.rods.get(idx).push(c);
                });
            });
            return state;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return rods.equals(state.rods);
        }

        @Override
        public int hashCode() {
            return Objects.hash(rods);
        }
    }


    private static int getMoves(List<PriorityQueue<Integer>> rodsueue) {

        List<Stack<Integer>> rods = new ArrayList<>();

        IntStream.range(0, rodsueue.size()).forEach(idx -> {
            Stack<Integer> stack = new Stack<>();
            rods.add(idx, stack);
            while (!rodsueue.get(idx).isEmpty()) {
                stack.push(rodsueue.get(idx).poll());
            }
        });

        State state = new State();
        state.level = 0;
        state.rods = rods;

        Set<State> visited = new HashSet<>();
        visited.add(state);

        Queue<State> queue = new LinkedList<>();
        queue.add(state);

        while (!queue.isEmpty()) {
            State poll = queue.poll();

            if (isFinishState(poll)) {
                return poll.level;
            }

            List<State> possibleMoves = getPossibleMoves(poll);

            possibleMoves.forEach(config -> {
                if (!visited.contains(config)) {
                    queue.add(config);
                    visited.add(config);
                }
            });
        }

        return 0;
    }

    private static boolean isFinishState(State state) {
        return state.rods.get(1).isEmpty() && state.rods.get(2).isEmpty() && state.rods.get(3).isEmpty();
    }

    private static List<State> getPossibleMoves(State poll) {
        List<State> newStates = new ArrayList<>();

        IntStream.range(0, poll.rods.size()).forEach(idx -> {

            if (poll.rods.get(idx).isEmpty()) {
                return;
            }

            for(int i=0; i<4; i++) {

                if (i == idx) {
                    continue;
                }

                State newState = poll.clone();
                newState.level = poll.level + 1;

                Integer pop = newState.rods.get(idx).pop();
                Stack<Integer> targetRod = newState.rods.get(i);

                if (!targetRod.isEmpty() && targetRod.peek() < pop) {
                    continue; //can not add to smaller
                }

                targetRod.push(pop);
                newStates.add(newState);
            }

        });

        return newStates;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int N = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        List<PriorityQueue<Integer>> rodsueue = Arrays.asList(
                new PriorityQueue<>(Collections.reverseOrder()),
                new PriorityQueue<>(Collections.reverseOrder()),
                new PriorityQueue<>(Collections.reverseOrder()),
                new PriorityQueue<>(Collections.reverseOrder())
        );

        for (int i=0; i<N; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            rodsueue.get(aItem - 1).add(i + 1);
        }

        System.out.print(getMoves(rodsueue));

        scanner.close();
    }
}
