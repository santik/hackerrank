package distantbarcodes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public static int[] rearrangeBarcodes(int[] barcodes) {

        Map<Integer, Integer> countes = new HashMap<>();

        PriorityQueue<Barcode> counts = new PriorityQueue<Barcode>(barcodes.length, new Comparator<Barcode>() {
            @Override
            public int compare(Barcode x, Barcode y) {
                if (x.count < y.count) return 1;
                if (x.count > y.count) return -1;
                return 0;
            }
        });

        for (int i = 0; i < barcodes.length; i++) {
            Integer count = countes.getOrDefault(barcodes[i], 0);
            countes.put(barcodes[i],count + 1);
        }

        countes.forEach((integer, integer2) -> counts.offer(new Barcode(integer, integer2)));

        int[] reordered = new int[barcodes.length];

        int prevMax = 0;
        int i = 0;
        while (true) {

            Barcode max = counts.poll();
            if (max == null) {
                break;
            }
            if (max.barcode == prevMax) {
                Barcode tmp = max;
                max = counts.poll();
                counts.offer(tmp);
            }

            reordered[i] = max.barcode;
            max.count --;
            if (max.count > 0) {
                counts.offer(max);
            }
            i++;
            prevMax = max.barcode;
        }

        return reordered;
    }

    private static class Barcode {

        public Barcode(int barcode, int count) {
            this.barcode = barcode;
            this.count = count;
        }

        int barcode;
        int count;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{7,7,7,8,5,7,5,5,5,8};
        System.out.println(Arrays.toString(rearrangeBarcodes(arr)));
    }

}