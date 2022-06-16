package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-06-16 18:57
 */
public class Leet983 {

    public static void main(String[] args) {
        System.out.println(mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15}));
        System.out.println(mincostTickets(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31, 53, 86, 97, 234}, new int[]{2, 7, 15}));
    }

    public static int mincostTickets(int[] days, int[] costs) {
        return dp(days, costs);
    }

    public static int dp(int[] days, int[] costs) {
        int N = days.length;

        int[] dp = new int[N];

        for (int i = N - 1; i >= 0; i--) {
            int cost1 = costs[0];
            int cost2 = costs[1];
            int cost3 = costs[2];

            int index1 = i + 1;
            int index2 = binSearch(days, days[i] + 6) + 1;
            int index3 = binSearch(days, days[i] + 29) + 1;

            dp[i] = Math.min(cost1 + pick(dp, index1), Math.min(cost2 + pick(dp, index2), cost3 + pick(dp, index3)));
        }

        return dp[0];
    }

    public static int pick(int[] dp, int i) {
        if (i >= dp.length) {
            return 0;
        }
        return dp[i];
    }

    public static int binSearch(int[] days, int target) {
        int l = 0;
        int r = days.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (days[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l - 1;
    }


    public static int dfs(int[] days, int[] costs, int i) {
        if (i >= days.length) {
            return 0;
        }

        int cost1 = costs[0];
        int cost2 = costs[1];
        int cost3 = costs[2];

        int index1 = i + 1;
        int index2 = binSearch(days, days[i] + 6) + 1;
        int index3 = binSearch(days, days[i] + 29) + 1;

        return Math.min(cost1 + dfs(days, costs, index1), Math.min(cost2 + dfs(days, costs, index2), cost3 + dfs(days, costs, index3)));
    }


}
