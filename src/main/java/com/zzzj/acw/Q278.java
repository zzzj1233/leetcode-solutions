package com.zzzj.acw;

import java.util.Scanner;

public class Q278 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int sum = scanner.nextInt();

        int[] nums = new int[N];

        for (int i = 0; i < N; i++)
            nums[i] = scanner.nextInt();

        int[] dp = new int[sum + 1];

        dp[0] = 1;

        for (int i = 0; i < N; i++) {

            for (int j = sum; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }

        }

        System.out.println(dp[sum]);
    }

}
