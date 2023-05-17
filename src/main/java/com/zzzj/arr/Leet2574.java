package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-05-12 16:16
 */
public class Leet2574 {


    public static void main(String[] args) {
        System.out.println(Arrays.toString(leftRigthDifference(new int[]{10, 4, 8, 3})));
    }


    public static int[] leftRigthDifference(int[] nums) {
        int N = nums.length;

        int[] leftSum = new int[N];
        int[] rightSum = new int[N];

        for (int i = 1; i < N; i++) {
            leftSum[i] = leftSum[i - 1] + nums[i - 1];
        }

        for (int i = N - 2; i >= 0; i--) {
            rightSum[i] = rightSum[i + 1] + nums[i + 1];
        }

        int[] ans = new int[N];

        for (int i = 0; i < N; i++) {
            ans[i] = Math.abs(leftSum[i] - rightSum[i]);
        }

        return ans;
    }

}
