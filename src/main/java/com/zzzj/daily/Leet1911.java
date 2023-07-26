package com.zzzj.daily;

public class Leet1911 {

    public static void main(String[] args) {

        System.out.println(maxAlternatingSum(new int[]{4, 2, 5, 3}));

        System.out.println(maxAlternatingSum(new int[]{5, 6, 7, 8}));

        System.out.println(maxAlternatingSum(new int[]{6, 2, 1, 2, 4, 5}));

    }

    public static long maxAlternatingSum(int[] nums) {
        int N = nums.length;

        int even = nums[0];
        int odd = 0;

        for (int i = 1; i < N; i++) {
            even = Math.max(odd + nums[i], even);
            odd = Math.max(even - nums[i], odd);
        }

        return Math.max(even, odd);
    }

    public static long dfs(int[] nums, int index, boolean even) {
        if (index >= nums.length) return 0;

        if (even) {
            return Math.max(dfs(nums, index + 1, false) + nums[index], dfs(nums, index + 1, true));
        } else {
            return Math.max(dfs(nums, index + 1, true) - nums[index], dfs(nums, index + 1, false));
        }
    }

}
