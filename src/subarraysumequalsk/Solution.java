package subarraysumequalsk;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public static int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> minuses = new HashMap<>();
        int total = 0;

        if (nums[0] == k) {
            total++;
        }

        minuses.put(nums[0], 1);

        int newSum;
        int prevSum = nums[0];
        for (int i=1; i<nums.length; i++) {
            newSum = prevSum + nums[i];

            Integer number = minuses.get(newSum - k);
            if (number != null) {
                total += number;
            }

            if (newSum == k) {
                total++;
            }
            minuses.merge(newSum, 1, Integer::sum);
            prevSum = newSum;
        }

        return total;

    }

    public static void main(String[] args) {
        int k = 2;
        int[] arr = new int[]{1,1,1};
        int i = subarraySum(arr, k);
        System.out.print(i);
    }
}
