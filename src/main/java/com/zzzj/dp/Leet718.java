package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-11-02 09:58
 */
public class Leet718 {

    /**
     * 输入：
     * A: [1,2,3,2,1]
     * B: [3,2,1,4,7]
     * 输出：3
     * 解释：
     * 长度最长的公共子数组是 [3, 2, 1] 。
     */

    public static void main(String[] args) {

        System.out.println(findLength(new int[]{1, 2, 3, 2, 1}, new int[]{3, 6, 1, 4}));

    }

    public static int findLength(int[] nums1, int[] nums2) {

        int N = nums1.length;

        int M = nums2.length;

        int[][] dp = new int[N][M];

        dp[0][0] = nums1[0] == nums2[0] ? 1 : 0;

        int ans = dp[0][0];

        for (int i = 1; i < M; i++) {
            if (nums1[0] == nums2[i]) {
                dp[0][i] = 1;
                ans = 1;
            }
        }

        for (int i = 1; i < N; i++) {
            if (nums1[i] == nums2[0]) {
                dp[i][0] = 1;
                ans = 1;
            }
        }

        for (int i = 1; i < N; i++) {

            for (int j = 1; j < M; j++) {

                dp[i][j] = (nums1[i] == nums2[j] ? 1 + dp[i - 1][j - 1] : 0);

                ans = Math.max(ans, dp[i][j]);
            }

        }

        return ans;
    }

}
