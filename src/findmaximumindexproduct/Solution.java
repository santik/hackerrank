package findmaximumindexproduct;
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    // Complete the solve function below.
    static long solve(int[] arr) {

        long max = 0;

        for (int i = 0; i < arr.length; i ++) {

            long left = -1;
            long right = -1;

            for (int j = i-1; j>=0; j--) {
                if (arr[j] > arr[i]) {
                    left = j + 1;
                    break;
                }
            }

            if (left == -1) {
                continue;
            }

            for (int j = i + 1; j< arr.length; j++) {
                if (arr[j] > arr[i]) {
                    right = j + 1;
                    break;
                }
            }

            if (right == -1) {
                continue;
            }

            max = Math.max(max, left*right);

        }

        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));


        int arrCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[arrCount];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int arrItr = 0; arrItr < arrCount; arrItr++) {
            int arrItem = Integer.parseInt(arrItems[arrItr]);
            arr[arrItr] = arrItem;
        }

        long result = solve(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
