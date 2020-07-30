package designerpdfviewer;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

    static int designerPdfViewer(int[] h, String word) {
        char[] chars = word.toCharArray();
        Optional<Integer> max = IntStream.range(0, chars.length)
                .mapToObj(i -> chars[i])
                .map(character -> getHeight(character, h))
                .max(Integer::compareTo);

        return chars.length * max.get();
    }

    private static Integer getHeight(Character character, int[] h) {
        int temp = (int)character;
        int temp_integer = 96;
        int index = temp - temp_integer - 1;
        return h[index];
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[] h = new int[26];

        String[] hItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < 26; i++) {
            int hItem = Integer.parseInt(hItems[i]);
            h[i] = hItem;
        }

        String word = scanner.nextLine();

        int result = designerPdfViewer(h, word);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
