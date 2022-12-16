package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2022-11-25 15:07
 */
public class Leet1558 {

    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{2, 4, 8, 16}));
        System.out.println(minOperations(new int[]{3, 2, 2, 4}));
        System.out.println(minOperations(new int[]{4, 2, 5}));
        System.out.println(minOperations(new int[]{2, 2}));
        System.out.println(minOperations(new int[]{1, 5}));
        System.out.println(minOperations(new int[]{1000000000}));
    }

    public static int minOperations(int[] nums) {

        int maxIdx = 0;

        int N = nums.length;

        if (N == 0) {
            return 0;
        }

        for (int i = 0; i < N; i++) {
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        int ans = 0;

        while (nums[maxIdx] != 0) {

            for (int i = 0; i < N; i++) {
                if (nums[i] % 2 != 0) {
                    nums[i]--;
                    ans++;
                }
            }

            if (nums[maxIdx] != 0) {
                for (int i = 0; i < N; i++) {
                    nums[i] /= 2;
                }

                ans++;
            }

        }

        return ans;
    }

}
