package com.zzzj.dp;

/**
 * @author Zzzj
 * @create 2022-06-14 20:17
 */
public class Leet376 {

    public static void main(String[] args) {
//        System.out.println(wiggleMaxLength(new int[]{1, 7, 4, 9, 2, 5}));

        System.out.println(wiggleMaxLength(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        System.out.println(wiggleMaxLength(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8}));
    }

    public static int wiggleMaxLength(int[] nums) {

        int N = nums.length;

        int[][] dp = new int[N][2];

        dp[0][0] = 1;
        dp[0][1] = 1;

        int ans = 1;

        for (int i = 1; i < N; i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;

            int num = nums[i];

            for (int j = 0; j < i; j++) {
                if (num > nums[j]) {
                    dp[i][0] = Math.max(dp[i][0], dp[j][1] + 1);
                } else if (num < nums[j]) {
                    dp[i][1] = Math.max(dp[i][1], dp[j][0] + 1);
                }
            }

            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));

        }

        return ans;
    }

}
