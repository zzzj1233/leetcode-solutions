package com.zzzj.dp;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-01-04 15:43
 */
public class Leet2363 {

    public static void main(String[] args) {

//        System.out.println(convertArray(new int[]{3, 2, 4, 5, 0}));

        System.out.println(convertArray(new int[]{2, 2, 3, 4}));

//        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            int N = 1000;

            int[] arr = ArrayUtil.generateArray(N, 0, 1000);

            if (convertArray(arr) != new Solution().convertArray(arr)) {
                System.out.println("Error");
                return;
            }
        }

        System.out.println("Ok");
    }

    static final int CAPACITY = 1001;

    static final int MAX = 1000;

    public static int convertArray(int[] nums) {

        int N = nums.length;

        int[][][] dp = new int[N + 1][CAPACITY][2];

        int[] minArr = new int[CAPACITY];

        for (int i = 1; i <= N; i++) {

            int preMin = dp[i - 1][0][0];

            int num = nums[i - 1];

            int min = dp[i - 1][0][0];

            // 递增
            //
            for (int j = 0; j < CAPACITY; j++) {
                min = Math.min(min, dp[i - 1][j][0]);
                dp[i][j][0] = min + Math.abs((num - j));
            }

            // 递减
            minArr[MAX] = dp[i - 1][MAX][1];

            for (int j = MAX - 1; j >= 0; j--) {
                minArr[j] = Math.min(minArr[j + 1], dp[i - 1][j][1]);
            }

            for (int j = 0; j < CAPACITY; j++) {
                dp[i][j][1] = minArr[j] + Math.abs((num - j));
            }

        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < CAPACITY; i++) {
            ans = Math.min(ans, Math.min(dp[N][i][0], dp[N][i][1]));
        }

        return ans;
    }


    private static class Solution {
        public int convertArray(int[] nums) {
            Worker worker1 = new Worker(), worker2 = new Worker();
            for (int i = 0, j = nums.length - 1; i < nums.length; i++, j--) {
                // 正向
                worker1.offer(nums[i]);
                // 反向
                worker2.offer(nums[j]);
            }
            // 答案为正向与反向结果的较小值
            return Math.min(worker1.ans, worker2.ans);
        }

        /**
         * 构造一个工具类帮助计算
         */
        private class Worker {
            private int[][] dp = new int[2][1001];
            private int cur = 0;
            public int ans = 0;

            private void offer(int num) {
                // 滚动数组操作dp
                int pre = cur;
                cur ^= 1;
                Arrays.fill(dp[cur], (int) 1e9);
                int min = (int) 1e9;
                int res = (int) 1e9;
                for (int i = 0; i <= 1000; i++) {
                    // 取前缀最小值
                    min = Math.min(min, dp[pre][i]);
                    // 当前值为前缀最小值加差值绝对值
                    dp[cur][i] = min + Math.abs(num - i);
                    // 记录当前最小值
                    res = Math.min(res, dp[cur][i]);
                }
                // 每轮更新一次答案
                ans = res;
            }
        }
    }

}
