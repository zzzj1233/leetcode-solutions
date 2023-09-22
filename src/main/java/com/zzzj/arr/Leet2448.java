package com.zzzj.arr;



import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2023-09-19 11:31
 */
public class Leet2448 {

    public static void main(String[] args) {

        System.out.println(minCost(new int[]{1, 3, 5, 2}, new int[]{2, 3, 1, 14}));

        System.out.println(minCost(new int[]{1, 3, 7, 9}, new int[]{1, 2, 3, 4}));

    }

    public static long minCost(int[] nums, int[] cost) {

        int N = nums.length;

        int[][] combined = new int[N][2];

        for (int i = 0; i < N; i++) {
            combined[i][0] = nums[i];
            combined[i][1] = cost[i];
        }

        Arrays.sort(combined, Comparator.comparingInt(o -> o[0]));

        long[] costPreSum = new long[N + 1];

        for (int i = 1; i <= N; i++)
            costPreSum[i] = costPreSum[i - 1] + combined[i - 1][1];

        long[] left = new long[N];

        for (int i = 1; i < N; i++) {
            long diff = combined[i][0] - combined[i - 1][0];
            left[i] = left[i - 1] + diff * costPreSum[i];
        }

        long[] right = new long[N];

        for (int i = N - 2; i >= 0; i--) {
            long diff = combined[i + 1][0] - combined[i][0];
            right[i] = right[i + 1] + diff * (costPreSum[N] - costPreSum[i + 1]);
        }

        long ans = Long.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            ans = Math.min(ans, left[i] + right[i]);
        }

        return ans;
    }


}
