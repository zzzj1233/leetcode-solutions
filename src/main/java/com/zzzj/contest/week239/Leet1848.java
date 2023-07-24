package com.zzzj.contest.week239;

/**
 * @author zzzj
 * @create 2023-07-18 18:03
 */
public class Leet1848 {

    public static void main(String[] args) {

        System.out.println(getMinDistance(new int[]{1, 2, 3, 4, 5}, 5, 3));

        System.out.println(getMinDistance(new int[]{1}, 1, 0));

        System.out.println(getMinDistance(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 1, 0));

    }

    public static int getMinDistance(int[] nums, int target, int start) {

        int N = nums.length;

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) if (nums[i] == target && Math.abs(i - start) < ans) ans = Math.abs(i - start);

        return ans;
    }

}
