package com.zzzj.arr;

import java.util.Arrays;

public class Leet2161 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(pivotArray(new int[]{9, 12, 5, 10, 14, 3, 10}, 10)));
    }

    public static int[] pivotArray(int[] nums, int pivot) {

        int N = nums.length;

        int[] ans = new int[N];

        int lt = 0;

        for (int i = 0; i < N; i++) {
            if (nums[i] < pivot) {
                ans[lt] = nums[i];
                lt++;
            }
        }

        int eq = lt;

        for (int i = 0; i < N; i++) {
            if (nums[i] == pivot) {
                ans[eq] = nums[i];
                eq++;
            }
        }

        int gt = eq;
        for (int i = 0; i < N; i++) {
            if (nums[i] > pivot) {
                ans[gt] = nums[i];
                gt++;
            }
        }

        return ans;
    }


}
