package com.zzzj.contest.dweek106;


import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-07-27 11:15
 */
public class Leet2731 {

    public static void main(String[] args) {

        System.out.println(sumDistance(new int[]{-2, 0, 2}, "RRRRR", 0));

        System.out.println(sumDistance(new int[]{1, 0}, "RL", 2));

    }

    public static int sumDistance(int[] nums, String s, int times) {

        int N = nums.length;

        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == 'L') nums[i] -= times;
            else nums[i] += times;
        }

        Arrays.sort(nums);

        final int MOD = 1000000007;

        long sum = 0;

        long ans = 0;

        for (int i = N - 1; i >= 0; i--) {
            sum += nums[i];
            ans += sum - ((long) nums[i] * (N - i));
        }

        return (int) (ans % MOD);
    }

}
