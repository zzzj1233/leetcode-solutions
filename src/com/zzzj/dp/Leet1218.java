package com.zzzj.dp;


/**
 * @author zzzj
 * @create 2021-11-05 11:06
 */
public class Leet1218 {

    /**
     * 示例 1：
     * <p>
     * 输入：arr = [1,2,3,4], difference = 1
     * 输出：4
     * 解释：最长的等差子序列是 [1,2,3,4]。
     * <p>
     * 示例2：
     * <p>
     * 输入：arr = [1,3,5,7], difference = 1
     * 输出：1
     * 解释：最长的等差子序列是任意单个元素。
     * <p>
     * 示例 3：
     * <p>
     * 输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
     * 输出：4
     * 解释：最长的等差子序列是 [7,5,3,1]。
     */
    public static void main(String[] args) {
//        System.out.println(longestSubsequence(new int[]{1, 2, 3, 4}, 1));
//        System.out.println(longestSubsequence(new int[]{1, 3, 5, 7}, 1));
        System.out.println(longestSubsequence(new int[]{1, 5, 7, 8, 5, 3, 4, 2, 1}, -2));

//        for (int i = 0; i < 10; i++) {
//            int[] arr = LeetUtils.newArray(10);
//            System.out.println(Arrays.toString(arr));
//            int diff = LeetUtils.random.nextInt(5) - 3;
//            System.out.println(longestSubsequence(arr, Math.abs(diff)));
//            System.out.println("=====");
//        }


//        System.out.println(dynamicPlanning(new int[]{1, 2, 3, 4}, 1));
//        System.out.println(dynamicPlanning(new int[]{1, 3, 5, 7}, 1));
//        System.out.println(dynamicPlanning(new int[]{1, 5, 7, 8, 5, 3, 4, 2, 1}, -2));
    }

    private static int dynamicPlanning(int[] arr, int difference) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                if (Math.abs(arr[j] - arr[i]) <= difference) {
                    dp[i][j] = 1 + (j - 1 < 0 ? 0 : dp[j][j - 1]);
                } else {
                    dp[i][j] = Math.max(j - 1 < 0 ? 0 : dp[i][j - 1], j - 1 < 0 ? 0 : dp[j][j - 1]);
                }
            }

        }

        return dp[n - 1][n - 1];
    }

    public static int longestSubsequence(int[] arr, int difference) {
        return 1 + process(arr, difference, 0, 1);
    }

    private static int process(int[] arr, int difference, int i, int j) {
        if (i >= arr.length || j >= arr.length) {
            return 0;
        }
        if (arr[j] - arr[i] == difference) {
            System.out.println(arr[j] + " - " + arr[i]);
            return 1 + process(arr, difference, j, j + 1);
        }
        return i == 0 ? process(arr, difference, j, j + 1) : process(arr, difference, i, j + 1);
    }


}
