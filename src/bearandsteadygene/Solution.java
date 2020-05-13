package bearandsteadygene;

import java.io.*;
import java.util.*;

public class Solution {

    static int steadyGene(String gene) {

        Map<Character, Integer> chars = new HashMap<>();
        chars.put('A', 0);
        chars.put('C', 0);
        chars.put('G', 0);
        chars.put('T', 0);

        for (Character c : gene.toCharArray()) {
            chars.put(c, chars.get(c) + 1);
        }

        int length = gene.length();
        int max = length / 4;

        int leftPointer = 0;
        int rightPointer = 0;
        int min = length;
        while(rightPointer < length - 1){
            char fromRight = gene.charAt(rightPointer);
            rightPointer++;
            chars.put(fromRight, chars.get(fromRight) - 1);
            while(!overflow(chars, max)){
                min = Math.min(min, rightPointer - leftPointer);
                char fromLeft = gene.charAt(leftPointer);
                leftPointer++;
                chars.put(fromLeft, chars.get(fromLeft) + 1);
            }
        }

        return min;
    }

    private static boolean overflow(Map<Character, Integer> chars, int max) {
        return chars.entrySet().stream()
                .anyMatch(entry -> entry.getValue() > max);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String gene = scanner.nextLine();

        int result = steadyGene(gene);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
