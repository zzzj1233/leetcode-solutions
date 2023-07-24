package com.zzzj.contest.week353;

/**
 * @author zzzj
 * @create 2023-07-12 11:56
 */
public class Leet2772 {

    public static void main(String[] args) {

        System.out.println(checkArray(new int[]{4, 0, 3, 0}, 7));

    }

    public static boolean checkArray(int[] nums, int k) {

        int N = nums.length;

        int[] diff = new int[N];

        int start = 0;

        int eqIndex = 0;

        int sub = 0;

        for (int i = 1; i < N; i++) {

            sub -= diff[i];

            if (nums[i] == nums[i - 1] && nums[i] == nums[eqIndex]) {
                eqIndex = i;
            }

            if (i - start + 1 == k) {

                start = eqIndex + 1;

                eqIndex = start;

                sub += nums[eqIndex];

                if (start + k - 1 < N) {
                    diff[start + k - 1] -= nums[eqIndex];
                }

            } else if (nums[i] < nums[i - 1]) {
                return false;
            }

        }

        return false;
    }

}
