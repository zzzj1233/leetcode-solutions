package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-01-23 15:20
 */
public class Leet1906 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(minDifference(new int[]{4, 5, 2, 2, 7, 10}, LeetUtils.convertInts("[[2,3],[0,2],[0,5],[3,5]]"))));
    }

    public static int[] minDifference(int[] nums, int[][] queries) {
        int N = nums.length;

        int M = queries.length;

        int[] ans = new int[M];


        // 前缀和记录数字出现的次数
        int[][] preSum = new int[N + 1][101];

        for (int i = 1; i <= N; i++) {

            for (int j = 0; j <= 100; j++) {
                preSum[i][j] = preSum[i - 1][j];
            }

            preSum[i][nums[i - 1]]++;
        }


        for (int i = 0; i < M; i++) {
            int[] query = queries[i];

            int l = query[0];

            int r = query[1];

            int lastNum = -1;

            int min = Integer.MAX_VALUE;

            for (int j = 0; j <= 100; j++) {
                // j一定存在于 l ~ r
                if (preSum[r + 1][j] > preSum[l][j]) {
                    if (lastNum != -1) {
                        min = Math.min(min, j - lastNum);
                    }
                    lastNum = j;
                }
            }

            ans[i] = min == Integer.MAX_VALUE ? -1 : min;
        }

        return ans;
    }

}
