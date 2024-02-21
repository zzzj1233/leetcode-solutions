package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-02-20 16:12
 */
public class Leet3041 {

    public static void main(String[] args) {

        System.out.println(maxSelectedElements(new int[]{2, 1, 5, 1, 1}));

    }

    public static int maxSelectedElements(int[] nums) {

        Arrays.sort(nums);

        int N = nums.length;

        int M = nums[N - 1];

        int[] f = new int[M + 2];

        for (int i = 0; i < N; i++) {

            int num = nums[i];

            f[num + 1] = Math.max(f[num + 1], f[num] + 1);
            f[num] = Math.max(f[num], f[num - 1] + 1);
        }

        return Arrays.stream(f).max().orElse(0);
    }

}
