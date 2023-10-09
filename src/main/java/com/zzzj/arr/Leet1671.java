package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2023-10-07 17:30
 */
public class Leet1671 {

    public static void main(String[] args) {
//
//        System.out.println(minimumMountainRemovals(new int[]{2, 1, 1, 5, 6, 2, 3, 1}));
//
//        System.out.println(minimumMountainRemovals(new int[]{1, 16, 84, 9, 29, 71, 86, 79, 72, 12}));
//
//        // [23,47,63,72,81,99,88,55,21,33,32]
//        System.out.println(minimumMountainRemovals(new int[]{23, 47, 63, 72, 81, 99, 88, 55, 21, 33, 32}));

        System.out.println(minimumMountainRemovals(new int[]{100, 92, 89, 77, 74, 66, 64, 66, 64}));

    }

    public static int minimumMountainRemovals(int[] nums) {

        int N = nums.length;

        int ans = Integer.MAX_VALUE;

        int[] left = new int[N];

        int[] right = new int[N];

        for (int i = 1; i < N; i++) {

            left[i] = i;

            for (int j = i - 1; j >= 0; j--)
                if (nums[j] < nums[i])
                    left[i] = Math.min(left[i], left[j] + (i - j - 1));

        }

        for (int i = N - 1; i >= 0; i--) {

            right[i] = N - i - 1;

            for (int j = i + 1; j < N; j++)
                if (nums[j] < nums[i])
                    right[i] = Math.min(right[i], right[j] + (j - i - 1));

        }

        for (int i = 1; i < N - 1; i++) {
            if (left[i] != i && right[i] != (N - i - 1))
                ans = Math.min(ans, left[i] + right[i]);
        }


        return ans;
    }

}
