package waiter;

import java.io.*;
import java.util.*;

public class Solution {

    static List<Stack<Integer>> aStacks = new ArrayList<>();
    static List<Stack<Integer>> bStacks = new ArrayList<>();
    private static ArrayList<Integer> primes;

    static int[] waiter(int[] number, int q) {

        generatePrimeNumbers(q);

        Stack<Integer> platesPile = new Stack<>();
        for (int num : number) {
            platesPile.push(num);
        }

        aStacks.add(platesPile);
        bStacks.add(new Stack<>());


        for (int i = 1; i <= q; i++) {

            Stack<Integer> bPile = new Stack<>();
            Stack<Integer> aPile = new Stack<>();

            Stack<Integer> pile = aStacks.get(i - 1);

            while (!pile.isEmpty()) {
                Integer plateNumber = pile.pop();

                if (plateNumber % primes.get(i) == 0) {
                    bPile.push(plateNumber);
                } else {
                    aPile.push(plateNumber);
                }
            }

            aStacks.add(i, aPile);
            bStacks.add(i, bPile);
        }

        int[] result = new int[number.length];
        int pointer = 0;

        for (Stack<Integer> stack : bStacks) {
            while (!stack.isEmpty()) {
                result[pointer] = stack.pop();
                pointer++;
            }
        }

        for (Stack<Integer> stack : aStacks) {
            while (!stack.isEmpty()) {
                result[pointer] = stack.pop();
                pointer++;
            }
        }

        return result;
    }

    public static void generatePrimeNumbers(int n) {

        primes = new ArrayList<>();
        primes.add(1);

        for (int i = 2; i < Integer.MAX_VALUE; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
            if (primes.size() == n + 1) {
                return;
            }
        }
    }

    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nq = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nq[0].trim());

        int q = Integer.parseInt(nq[1].trim());

        int[] number = new int[n];

        String[] numberItems = scanner.nextLine().split(" ");

        for (int numberItr = 0; numberItr < n; numberItr++) {
            int numberItem = Integer.parseInt(numberItems[numberItr].trim());
            number[numberItr] = numberItem;
        }

        int[] result = waiter(number, q);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bufferedWriter.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
