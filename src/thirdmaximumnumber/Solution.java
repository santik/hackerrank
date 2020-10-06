package thirdmaximumnumber;

class Solution {
    public static int thirdMax(int[] nums) {

        Integer max1 = nums[0];
        Integer max2 = null;
        Integer max3 = null;

        for (int i=1; i<nums.length; i++) {

            if (nums[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
            } else if((max2 == null || nums[i] > max2) && nums[i] < max1) {
                if (max3 != max2) {
                    max3 = max2;
                }
                max2 = nums[i];
            } else if((max3 == null || nums[i] > max3) && (max2 != null && nums[i] < max2)) {
                max3 = nums[i];
            }
        }

        if (max3 != null) {
            return max3;
        }
        return max1;
    }

    public static void main(String[] args) {

        int[] arr = new int[]{2,2,3,1};

        System.out.println(thirdMax(arr));

    }
}