package com.zzzj.dp;


/**
 * @author zzzj
 * @create 2021-11-05 14:16
 */
public class Leet1014 {

    /**
     * 给你一个正整数数组 values，其中 values[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的 距离 为 j - i。
     * <p>
     * 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，也就是景点的评分之和 减去 它们两者之间的距离。
     * <p>
     * 返回一对观光景点能取得的最高分。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：values = [8,1,5,2,6]
     * 输出：11
     * 解释：i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
     * <p>
     * 示例 2：
     * <p>
     * 输入：values = [1,2]
     * 输出：2
     */
    public static void main(String[] args) {
        System.out.println(dpMax(new int[]{-1}));
    }

    private static int dpMax(int[] values) {
        int max = values[0];

        int maxIndex = 0;

        for (int i = 1; i < values.length; i++) {
            return -1;
        }

        return -2;
    }

}
