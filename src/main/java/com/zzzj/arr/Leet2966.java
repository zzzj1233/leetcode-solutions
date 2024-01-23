package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-12-27 16:49
 */
public class Leet2966 {

    public static void main(String[] args) {

        System.out.println(Arrays.deepToString(divideArray(
                new int[]{1, 3, 4, 8, 7, 9, 3, 5, 1},
                2
        )));

    }

    public static int[][] divideArray(int[] nums, int k) {

        int N = nums.length;

        Arrays.sort(nums);

        int M = N / 3;

        int[][] ans = new int[M][3];

        for (int i = 0; i < M; i++) {

            int numIndex = i * 3;

            ans[i][0] = nums[numIndex];

            if (nums[numIndex + 1] - nums[numIndex] > k)
                return new int[0][0];

            ans[i][1] = nums[numIndex + 1];

            if (nums[numIndex + 2] - nums[numIndex + 1] > k || nums[numIndex + 2] - nums[numIndex] > k)
                return new int[0][0];

            ans[i][2] = nums[numIndex + 2];

        }

        return ans;
    }

}
