package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-10-26 16:51
 */
public class Leet1187 {

    public static void main(String[] args) {

        System.out.println(makeArrayIncreasing(new int[]{1, 1, 3}, new int[]{1, 2, 4}));

    }

    public static int makeArrayIncreasing(int[] arr1, int[] arr2) {

        arr2 = Arrays.stream(arr2)
                .sorted()
                .distinct()
                .toArray();

        final int N = arr1.length;

        final int M = arr2.length;

        final int SELF = M;

        int[][] dp = new int[N][M + 1];

        for (int i = 0; i < M; i++) {
            dp[0][i] = arr1[0] == arr2[i] ? 0 : 1;
        }

        for (int i = 1; i < N; i++) {

            int index = binarySearch(arr2, arr1[i]);

            Arrays.fill(dp[i], Integer.MAX_VALUE);

            // self
            if (arr1[i] > arr1[i - 1])
                dp[i][SELF] = Math.min(
                        dp[i][SELF],
                        dp[i - 1][SELF]
                );

            for (int j = 0; j <= index; j++) {
                dp[i][SELF] = Math.min(
                        dp[i][SELF],
                        dp[i - 1][j]
                );
            }

            // 变成j的最小cost
            for (int j = 0; j < M; j++) {
                if (j != 0 && dp[i - 1][j - 1] != Integer.MAX_VALUE)
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                if (arr2[j] > arr1[i - 1] && dp[i - 1][SELF] != Integer.MAX_VALUE)
                    dp[i][j] = Math.min(
                            dp[i][j],
                            dp[i - 1][SELF] + 1
                    );
            }

        }

        int min = Arrays.stream(dp[N - 1]).min().orElse(-1);

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private static int binarySearch(int[] arr2, int value) {

        int left = 0;

        int right = arr2.length - 1;

        int res = -1;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (arr2[mid] == value)
                return mid - 1;
            else if (arr2[mid] > value) {
                right = mid - 1;
            } else {
                res = mid;
                left = mid + 1;
            }

        }

        return res;
    }


}
