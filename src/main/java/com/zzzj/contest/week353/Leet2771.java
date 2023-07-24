package com.zzzj.contest.week353;

/**
 * @author zzzj
 * @create 2023-07-12 14:56
 */
public class Leet2771 {

    public static void main(String[] args) {

        System.out.println(maxNonDecreasingLength(new int[]{2, 3, 1}, new int[]{1, 2, 1}));

        System.out.println(maxNonDecreasingLength(new int[]{1, 3, 2, 1}, new int[]{2, 2, 3, 4}));

        System.out.println(maxNonDecreasingLength(new int[]{1, 1}, new int[]{2, 2}));

        System.out.println(maxNonDecreasingLength(new int[]{1, 8}, new int[]{10, 1}));

        System.out.println(maxNonDecreasingLength(new int[]{3, 19, 13, 19}, new int[]{20, 18, 7, 14}));

    }

    public static int maxNonDecreasingLength(int[] nums1, int[] nums2) {

        int N = nums1.length;

        int[][] dp = new int[N][2];
        dp[0][0] = 1;
        dp[0][1] = 1;

        int ans = 1;

        for (int i = 1; i < N; i++) {

            int n1 = nums1[i];

            int n2 = nums2[i];

            dp[i][0] = 1;
            dp[i][1] = 1;

            if (n1 >= nums1[i - 1]) {
                dp[i][0] = dp[i - 1][0] + 1;
            }
            if (n1 >= nums2[i - 1]) {
                dp[i][0] = Math.max(dp[i][0], dp[i - 1][1] + 1);
            }

            if (n2 >= nums1[i - 1]) {
                dp[i][1] = dp[i - 1][0] + 1;
            }
            if (n2 >= nums2[i - 1]) {
                dp[i][1] = Math.max(dp[i][1], dp[i - 1][1] + 1);
            }

            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
        }

        return ans;
    }

}
