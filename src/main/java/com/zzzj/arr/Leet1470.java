package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-05-12 16:58
 */
public class Leet1470 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(shuffle(new int[]{2, 5, 1, 3, 4, 7}, 3)));
    }

    public static int[] shuffle(int[] nums, int n) {
        int N = nums.length;

        int[] ans = new int[N];

        for (int i = 0, j = 0; i < N; i += 2, j++) {
            ans[i] = nums[j];
            ans[i + 1] = nums[j + n];
        }

        return ans;
    }

}
