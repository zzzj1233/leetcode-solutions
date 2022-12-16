package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2022-11-23 14:44
 */
public class Leet2219 {

    // 给你一个下标从 0 开始的整数数组 nums ，数组长度为 n 。
    //
    //nums 在下标 i （0 <= i < n）处的 总分 等于下面两个分数中的 最大值 ：
    //
    // nums 前 i + 1 个元素的总和
    // nums 后 n - i 个元素的总和
    public static long maximumSumScore(int[] nums) {
        int n = nums.length;

        long[] pre = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }

        long res = Long.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            res = Math.max(res, Math.max(pre[i + 1], pre[n] - pre[i]));
        }

        return res;
    }

}
