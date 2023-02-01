package com.zzzj.arr;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-01-28 15:37
 */
public class Leet1508 {

    public static void main(String[] args) {
        System.out.println(rangeSum(
                new int[]{1, 2, 3, 4, 5},
                5,
                1,
                5
        ));
    }

    public static int rangeSum(int[] nums, int n, int left, int right) {
        int[] preSum = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        List<Integer> sums = new ArrayList<>();

        int N = preSum.length;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                sums.add(preSum[j] - preSum[i]);
            }
        }

        sums.sort(Comparator.comparingInt(o -> o));

        long sum = 0;

        while (left <= right) {
            sum += sums.get(left - 1);
            left++;
        }

        final int MOD = 1000000007;

        return (int) (sum % MOD);
    }

}
