package com.zzzj.contest.week354;

public class Leet6889 {

    public static void main(String[] args) {

        System.out.println(sumOfSquares(new int[]{1, 2, 3, 4}));

        System.out.println(sumOfSquares(new int[]{2, 7, 1, 19, 18, 3}));

    }

    public static int sumOfSquares(int[] nums) {
        int N = nums.length;

        int sum = 0;

        for (int i = 0; i < N; i++) {
            if (N % (i + 1) == 0) {
                sum += nums[i] * nums[i];
            }
        }

        return sum;
    }

}
