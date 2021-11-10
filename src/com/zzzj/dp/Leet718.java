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
        System.out.println(dynamicPlanning(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7}));
    }

    public static int findLength(int[] nums1, int[] nums2) {
        return findLength(nums1, nums2, 0, 0);
    }

    private static int dynamicPlanning(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        int[][] dp = new int[n1][n2];

        int max = 0;

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = 1;
                    if (i - 1 >= 0 && j - 1 >= 0 && dp[i - 1][j - 1] > 0) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }
        }


        return max;
    }

    private static int findLength(int[] nums1, int[] nums2, int i, int j) {
        if (i >= nums1.length || j >= nums2.length) {
            return 0;
        }

        if (nums1[i] == nums2[j]) {
            return 1 + findLength(nums1, nums2, i + 1, j + 1);
        }

        return Math.max(findLength(nums1, nums2, i + 1, j), findLength(nums1, nums2, i, j + 1));
    }

}
