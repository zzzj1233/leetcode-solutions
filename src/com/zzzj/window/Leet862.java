package com.zzzj.window;

/**
 * @author Zzzj
 * @create 2021-12-19 14:54
 */
public class Leet862 {


    public static void main(String[] args) {
        System.out.println(shortestSubarray(new int[]{84, -37, 32, 40, 95}, 167));
    }


    public static int shortestSubarray(int[] nums, int k) {

        int l = 0;
        int r = 0;

        int sum = 0;

        int ans = Integer.MAX_VALUE;

        for (; r < nums.length; r++) {
            sum += nums[r];
            // 尝试减小窗口
            while (sum >= k) {
                ans = Math.min(ans, r - l + 1);
                sum -= nums[l++];
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
