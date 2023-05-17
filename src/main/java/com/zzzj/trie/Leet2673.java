package com.zzzj.trie;

/**
 * @author zzzj
 * @create 2023-05-17 14:49
 */
public class Leet2673 {

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
