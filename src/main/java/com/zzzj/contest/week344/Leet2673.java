package com.zzzj.contest.week344;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-01 18:16
 */
public class Leet2673 {

    public static void main(String[] args) {

        while (true) {
            int n = 7;
            int[] cost = ArrayUtil.generateArray(n, 1, 5);
            if (minIncrements(n, cost) != Solution.minIncrements(n, cost)) {
                System.out.println("n = " + n);
                System.out.println("cost = " + Arrays.toString(cost));
                System.out.println("minIncrements = " + minIncrements(n, cost));
                System.out.println("Solution = " + Solution.minIncrements(n, cost));
                break;
            }
        }

    }

    public static int minIncrements(int n, int[] cost) {
        ans = 0;
        dfs(1, cost);
        return ans;
    }

    static int ans;

    public static int dfs(int current, int[] cost) {
        if (current > cost.length)
            return 0;

        int c = cost[current - 1];

        int left = dfs(current << 1, cost);

        int right = dfs((current << 1) + 1, cost);

        ans += Math.max(left, right) - Math.min(left, right);

        return (Math.max(left, right) << 1) + c;
    }

    private static class Solution {

        public static void main(String[] args) {
            System.out.println(minIncrements(7, new int[]{1, 5, 2, 2, 3, 3, 1}));
        }

        static int ans = 0;

        public static int minIncrements(int n, int[] cost) {
            ans = 0;
            incr(1, n, cost);
            return ans;
        }

        public static int incr(int current, int n, int[] cost) {
            int left = current << 1;

            if (left > n) {
                return 0;
            }

            int right = left + 1;

            int leftCost = incr(left, n, cost) + cost[left - 1];
            int rightCost = incr(right, n, cost) + cost[right - 1];

            ans += Math.max(leftCost, rightCost) - Math.min(leftCost, rightCost);

            return Math.max(leftCost, rightCost);
        }

    }
}
