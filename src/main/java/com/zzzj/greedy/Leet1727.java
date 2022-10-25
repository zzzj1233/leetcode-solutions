package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

public class Leet1727 {

    public static void main(String[] args) {
        System.out.println(largestSubmatrix(LeetUtils.convertInts("[[1,1,0],[1,0,1]]")));
    }

    public static int largestSubmatrix(int[][] matrix) {

        int M = matrix.length;
        int N = matrix[0].length;

        int ans = 0;

        for (int i = 0; i < M; i++) {

            int[] height = new int[N];

            for (int j = 0; j < N; j++) {
                height[j] = matrix[i][j] == 1 ? height[j] + 1 : 0;
            }

            Arrays.sort(height);

            for (int j = 0; j < N; j++) {
                ans = Math.max(ans, height[j] * (N - j));
            }

        }

        return ans;
    }

}
