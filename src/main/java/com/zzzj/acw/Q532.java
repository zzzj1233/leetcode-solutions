package com.zzzj.acw;

import java.util.Arrays;
import java.util.Scanner;

public class Q532 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        for (int i = 0; i < N; i++) {

            int M = scanner.nextInt();

            int[] nums = new int[M];

            for (int j = 0; j < M; j++) {
                nums[j] = scanner.nextInt();
            }

            Arrays.sort(nums);

            boolean[] dp = new boolean[nums[M - 1] + 1];

            dp[nums[0]] = true;

            int cnt = 1;


        }

    }

}
