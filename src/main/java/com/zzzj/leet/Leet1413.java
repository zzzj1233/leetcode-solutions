package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-12-09 15:56
 */
public class Leet1413 {


    public static void main(String[] args) {
        System.out.println(minStartValue(new int[]{-3, 2, -3, 4, 2}));
    }

    public static int minStartValue(int[] nums) {
        int N = nums.length;

        int[] preSum = new int[N + 1];

        int ans = 0;

        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
            ans = Math.min(preSum[i], ans);
        }

        return Math.abs(ans) + 1;
    }

}
