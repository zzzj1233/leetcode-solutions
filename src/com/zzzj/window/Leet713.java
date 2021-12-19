package com.zzzj.window;

/**
 * @author Zzzj
 * @create 2021-12-19 13:59
 */
public class Leet713 {

    public static void main(String[] args) {
        System.out.println(numSubarrayProductLessThanK(new int[]{1, 2, 3}, 0));
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {

        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int product = nums[i];

            if (product < k) {
                ans++;
                for (int j = i + 1; j < nums.length; j++) {
                    product *= nums[j];
                    if (product < k) {
                        ans++;
                    } else {
                        break;
                    }
                }
            }

        }

        return ans;
    }

}
