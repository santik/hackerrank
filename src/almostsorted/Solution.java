package almostsorted;

import java.util.*;

public class Solution {

    // Complete the almostSorted function below.
    static void almostSorted(int[] arr) {

        if (arr.length == 1) {
            System.out.println("yes");
            return;
        }

        int startIndex = 0;
        int finishIndex = -1;

        for (int i=1; i<arr.length; i++) {

            if (arr[i] > arr[i-1] && finishIndex == -1) {
                startIndex = i;
            } else if (arr[i] < arr[i-1]) {
                finishIndex = i;
            }
        }

        if (finishIndex == -1) {
            System.out.println("yes");
            return;
        }


        if (
                finishIndex - startIndex == 1 || //neighbours
                (finishIndex - startIndex > 3 && arr[startIndex+1] < arr[startIndex+2]) // random swap
        ) {
            swap(arr, startIndex, finishIndex);
            if (isSorted(arr)) {
                System.out.println("yes");
                System.out.print("swap " + (startIndex + 1) + " " + (finishIndex + 1));
                return;
            } else {
                System.out.println("no");
                return;
            }
        }

        reverse(arr, startIndex, finishIndex);

        if (isSorted(arr)) {
            System.out.println("yes");
            System.out.print("reverse " + (startIndex + 1) + " " + (finishIndex + 1));
        } else {
            System.out.println("no");
        }


    }

     static boolean isSorted(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }

        return true;
    }

    private static void reverse(int[] arr, int startIndex, int finishIndex) {

        int lenght = finishIndex - startIndex + 1;
        for(int i = startIndex; i< startIndex + lenght/2; i++){
            int temp = arr[i];
            int endIndex = finishIndex - (i- startIndex);
            arr[i] = arr[endIndex];
            arr[endIndex] = temp;
        }
    }

    private static void swap(int[] arr, int startIndex, int finishIndex) {
        int tmp = arr[startIndex];
        arr[startIndex] = arr[finishIndex];
        arr[finishIndex] = tmp;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        almostSorted(arr);

        scanner.close();
    }
}
