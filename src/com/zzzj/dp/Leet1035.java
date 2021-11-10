package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-11-02 15:39
 */
public class Leet1035 {

    public static void main(String[] args) {
        System.out.println(maxUncrossedLines(new int[]{1, 3, 7, 1, 7, 5}, new int[]{1, 9, 2, 5, 1}));
        System.out.println(dynamicPlanning(new int[]{1, 3, 7, 1, 7, 5}, new int[]{1, 9, 2, 5, 1}));
    }

    private static int dynamicPlanning(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        if (n1 == 0 || n2 == 0) {
            return 0;
        }

        int[][] dp = new int[n1][n2];

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = i - 1 >= 0 && j - 1 >= 0 ? dp[i - 1][j - 1] + 1 : 1;
                } else {
                    dp[i][j] = Math.max(i - 1 >= 0 ? dp[i - 1][j] : 0, j - 1 >= 0 ? dp[i][j - 1] : 0);
                }
            }

        }

        return dp[n1 - 1][n2 - 1];
    }

    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        return violentRecursion(nums1, nums2, 0, 0);
    }

    private static int violentRecursion(int[] nums1, int[] nums2, int i, int j) {
        if (i >= nums1.length || j >= nums2.length) {
            return 0;
        }

        if (nums1[i] == nums2[j]) {
            // 必须往后找
            return 1 + violentRecursion(nums1, nums2, i + 1, j + 1);
        }

        return Math.max(violentRecursion(nums1, nums2, i, j + 1), Math.max(violentRecursion(nums1, nums2, i + 1, j), violentRecursion(nums1, nums2, i + 1, j + 1)));
    }

}
