package com.zzzj.contest.dweek106;


import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-21 16:11
 */
public class Leet2731 {

    public static void main(String[] args) {

        System.out.println(sumDistance(
                new int[]{-2, 0, 2},
                "RLL",
                3
        ));

        System.out.println(sumDistance(
                new int[]{1, 3, 4, 5},
                "RRRR",
                0
        ));

    }

    static final int MOD = 1000000007;

    public static int sumDistance(int[] nums, String s, int d) {

        int N = nums.length;

        long[] temp = new long[N];

        for (int i = 0; i < N; i++) {
            temp[i] = (long) nums[i] + (s.charAt(i) == 'L' ? -d : d);
        }

        Arrays.sort(temp);

        long ans = 0;

        long sum = 0;

        for (int i = 1; i < N; i++) {
            // 求贡献
            long l = temp[i] - temp[i - 1];
            int right = N - i;
            int left = i;
            ans += ((l * right ) % MOD) * left;
            ans %= MOD;
        }

        return (int) (ans % MOD);
    }

}
