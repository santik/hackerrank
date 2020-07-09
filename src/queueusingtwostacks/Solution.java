package queueusingtwostacks;

import java.io.*;
import java.util.*;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int arrCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] arr = new String[arrCount];

        Queue<String> queue = new LinkedList<>();

        for (int qItr = 0; qItr < arrCount; qItr++) {
            String[] command = scanner.nextLine().split(" ");
            if (command[0].equals("1")) {
                queue.add(command[1]);
            } else if (command[0].equals("2")) {
                queue.poll();
            } else if (command[0].equals("3")) {
                bufferedWriter.write(queue.peek());
                bufferedWriter.newLine();
            }
        }

        bufferedWriter.close();
    }
}
