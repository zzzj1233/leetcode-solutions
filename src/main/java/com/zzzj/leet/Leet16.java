package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-09-22 16:08
 */
public class Leet16 {

    private int target;

    public static void main(String[] args) {
        System.out.println(new Leet16().threeSumClosest(new int[]{-55, -24, -18, -11, -7, -3, 4, 5, 6, 9, 11, 23, 33}, 0));
    }

    private boolean isClosest(int num1, Integer num2) {
        if (num2 == null) {
            return true;
        }
        return Math.abs(target - num1) < Math.abs(target - num2);
    }

    public int threeSumClosest(int[] nums, int target) {
        this.target = target;

        Arrays.sort(nums);

        Integer closestNum = null;

        for (int i = 0; i < nums.length; i++) {
            int numI = nums[i];

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int numJ = nums[j];
                int numK = nums[k];
                int sum = numJ + numK + numI;

                if (sum == target) {
                    return target;
                }

                if (isClosest(sum, closestNum)) {
                    closestNum = sum;
                }

                int nextJ = nums[j + 1];
                int sum2 = numK + nextJ + numI;

                int nextK = nums[k - 1];
                int sum3 = nextK + numJ + numI;

                if (isClosest(sum2, sum3)) {
                    j++;
                } else {
                    k--;
                }
            }
        }

        return closestNum;
    }

}