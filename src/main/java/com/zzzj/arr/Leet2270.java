package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2022-10-12 15:21
 */
public class Leet2270 {

    public static void main(String[] args) {
        System.out.println(waysToSplitArray(new int[]{10, 4, -8, 7}));
    }

    public static int waysToSplitArray(int[] nums) {

        int N = nums.length;

        int[] left = new int[N + 1];

        int[] right = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            left[i] = left[i - 1] + nums[i - 1];
        }

        for (int i = N - 1; i >= 0; i--) {
            right[i] = right[i + 1] + nums[i];
        }

        int ans = 0;

        for (int i = 1; i < N; i++) {
            if (left[i] >= right[i]) {
                ans++;
            }
        }

        return ans;
    }

}
