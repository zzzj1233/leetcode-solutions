package com.zzzj.bit;

/**
 * @author zzzj
 * @create 2023-06-09 11:41
 */
public class Leet2680 {

    public static void main(String[] args) {

        System.out.println(maximumOr(new int[]{8, 1, 2}, 2));

        // [88,43,61,72,13]
        // 6
        System.out.println(maximumOr(new int[]{88, 43, 61, 72, 13}, 6));

    }

    public static long maximumOr(int[] nums, int k) {

        int N = nums.length;

        long prefix = 0;

        long max = 0;

        int[] suffixTab = new int[32];

        for (int i = 0; i < N; i++) {

            int num = nums[i];

            for (int j = 0; j < 31; j++) {
                suffixTab[j] += (num >> j) & 1;
            }

        }

        for (int i = 0; i < N; i++) {

            int num = nums[i];

            for (int j = 0; j < 31; j++) {
                suffixTab[j] -= (num >> j) & 1;
            }

            int suffix = suffix(suffixTab);

            for (int j = 1; j <= k; j++) {

                long mul = num * (1L << j);

                max = Math.max(max, mul | prefix | suffix);

            }

            prefix |= num;

        }

        return max;
    }

    public static int suffix(int[] suffixTab) {

        int suffix = 0;

        for (int i = 0; i < 31; i++) {
            if (suffixTab[i] > 0) {
                suffix |= 1 << i;
            }
        }

        return suffix;
    }
}
