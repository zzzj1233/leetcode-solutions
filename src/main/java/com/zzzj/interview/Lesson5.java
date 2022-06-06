package com.zzzj.interview;


/**
 * @author Zzzj
 * @create 2022-06-03 14:11
 */
public class Lesson5 {


    // 搜索二叉树转双向链表
    // Leet 426
    // 二叉树递归套路
    public static void question2() {

    }

    /**
     * 没有重复节点
     *
     * @param preOrder 二叉树的前序遍历
     * @param inOrder  二叉树的中序遍历
     * @return 二叉树的后续遍历
     * <pre>
     * pre = {1, 2, 4, 5, 3, 6, 7}
     * in = {4, 2, 5, 1, 6, 3, 7}
     * post = {4, 5, 2, 6, 7, 3, 1}
     * </pre>
     * 延伸: Leet889
     */
    public static int[] question3(int[] preOrder, int[] inOrder) {
        return null;
    }


    /**
     * @return 最长递增子序列长度, 相等也不算递增
     * <p>
     * 动态规划 O(N²)
     * Leet 300
     * <p>
     * Expect: O(Log * N)
     */
    public static int question4(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        int ans = 0;

        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {

            dp[i] = 1;

            for (int j = 0; j < i; j++) {

                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

            }

            ans = Math.max(ans, dp[i]);

        }

        return ans;
    }

    /**
     * O(N * log N)
     */
    public static int question4Ext1(int[] nums) {
        return -1;
    }

    public static int question5() {

        return -1;
    }

    /**
     * @return 给定一个数组, 返回子数组的最大累加和
     * dp
     */
    public static int question6(int[] arr) {

        return -1;
    }


    /**
     * @return 给定一个矩阵, 返回子矩阵的最大累加和
     * dp
     */
    public static int question7(int[][] matrix) {
        return -1;
    }

}
