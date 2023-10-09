package com.zzzj.dp;

import javax.swing.plaf.MenuItemUI;

/**
 * @author zzzj
 * @create 2023-10-09 16:34
 */
public class Leet1458 {

    public static void main(String[] args) {

//        System.out.println(maxDotProduct(
//                new int[]{2, 1, -2, 5},
//                new int[]{3, 0, -6}
//        ));

        System.out.println(maxDotProduct(
                new int[]{-3, -8, 3, -10, 1, 3, 9},
                new int[]{9, 2, 3, 7, -9, 1, -8, 5, -1, -1}
        ));

        System.out.println(right(
                new int[]{-3, -8, 3, -10, 1, 3, 9},
                new int[]{9, 2, 3, 7, -9, 1, -8, 5, -1, -1}
        ));

    }

    public static int right(int[] nums1, int[] nums2) {
        return dfs(nums1, nums2, 0, 0);
    }

    public static int dfs(int[] nums1, int[] nums2, int x, int y) {

        if (x >= nums1.length || y >= nums2.length)
            return 0;

        int mul = nums1[x] * nums2[y];

        return Math.max(
                Math.max(
                        dfs(nums1, nums2, x + 1, y),
                        dfs(nums1, nums2, x, y + 1)
                ),
                Math.max(
                        dfs(nums1, nums2, x + 1, y + 1),
                        dfs(nums1, nums2, x + 1, y + 1) + mul
                )
        );
    }

    public static int maxDotProduct(int[] nums1, int[] nums2) {

        int N = nums1.length;

        int M = nums2.length;

        int[][] dp = new int[N][M];

        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < M; j++) {

                dp[i][j] = nums1[i] * nums2[j];

                if (i > 0)
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);

                if (j > 0)
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);

                if (i > 0 && j > 0)
                    dp[i][j] = Math.max(dp[i][j], Math.max(
                            dp[i - 1][j - 1],
                            dp[i - 1][j - 1] + nums1[i] * nums2[j]
                    ));

            }

        }

        return dp[N - 1][M - 1];
    }


}
