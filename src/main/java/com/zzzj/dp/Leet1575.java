package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-10-18 15:17
 */
public class Leet1575 {

    public static void main(String[] args) {

        System.out.println(countRoutes(new int[]{2, 3, 6, 8, 4}, 1, 3, 5));

        System.out.println(countRoutes(new int[]{4, 3, 1}, 1, 0, 6));

    }

    static final int MOD = 1000000007;

    public static int countRoutes(int[] locations, int start, int finish, int fuel) {
        int[][] memo = new int[locations.length][fuel + 1];
        for (int i = 0; i < locations.length; i++) Arrays.fill(memo[i], -1);
        return dfs(locations, start, finish, start, fuel, memo);
    }

    public static int dfs(int[] locations, int start, int finish, int cur, int fuel, int[][] memo) {
        if (fuel < 0)
            return 0;

        if (memo[cur][fuel] != -1)
            return memo[cur][fuel];

        int ans = cur == finish ? 1 : 0;

        int c = locations[cur];

        for (int i = 0; i < locations.length; i++) {

            if (i == cur)
                continue;

            ans = (ans + dfs(locations, start, finish, i, fuel - Math.abs(locations[i] - c), memo)) % MOD;
        }

        memo[cur][fuel] = ans;

        return ans;
    }

}
