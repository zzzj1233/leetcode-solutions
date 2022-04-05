package com.zzzj.hot;

/**
 * @author Zzzj
 * @create 2022-04-17 19:01
 */
public class Leet152 {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{-4, -3, -2}));
    }

    public static int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];

        int ans = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int max2 = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(max * nums[i], min * nums[i]), nums[i]);
            max = max2;
            ans = Math.max(ans, max);
        }

        return ans;
    }

}
