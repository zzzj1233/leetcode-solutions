package com.zzzj.hot;


import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-05-05 15:11
 */
public class Leet322 {

    public static void main(String[] args) {

//        System.out.println(coinChange(new int[]{4, 5, 8, 10}, 9));
////
//        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            final int[] coins = ArrayUtil.generateArray(LeetUtils.random.nextInt(50) + 1, 1, 100);
            int amount = LeetUtils.random.nextInt(coins.length * (LeetUtils.random.nextInt(10) + 1));

//            Arrays.sort(coins);

            if (coinChange(coins, amount) != right(coins, amount)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(coins));
                System.out.println(amount);
                System.out.println(coinChange(coins, amount));
                System.out.println(right(coins, amount));
                return;
            }
        }
        System.out.println("Ok");
    }

    // [2,5,10,1]
    // 27
    public static int coinChange(int[] coins, int amount) {
        int N = coins.length;

        int[][] dp = new int[N][amount + 1];

        // 每种硬币可以使用无限次

        // 只可以用0~i张硬盘时,凑出amount的最小数量

        for (int i = 1; i <= amount; i++) {
            if (i < coins[0]) {
                dp[0][i] = Integer.MAX_VALUE;
                continue;
            }
            int count = i / coins[0];
            int sub = i % coins[0];
            if (dp[0][sub] == Integer.MAX_VALUE) {
                dp[0][i] = Integer.MAX_VALUE;
            } else {
                dp[0][i] = dp[0][sub] + count;
            }
        }

        for (int i = 1; i < N; i++) {

            int coin = coins[i];

            for (int j = 1; j <= amount; j++) {

                if (j < coin) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }

                // 1. 不使用当前硬币
                int ways1 = dp[i - 1][j];

                // 2. 使用当前硬币
                int count = j / coin;

                dp[i][j] = ways1;

                for (int k = 1; k <= count; k++) {
                    int sub = j - (coin * k);
                    if (dp[i - 1][sub] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][sub] + k);
                }

            }

        }

        return dp[N - 1][amount] == Integer.MAX_VALUE ? -1 : dp[N - 1][amount];
    }


    public static int right(int[] coins, int amount) {
        int ans = dfs(coins, amount, 0, new HashMap<>());
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int dfs(int[] coins, int amount, int cur, Map<Integer, Integer> cache) {
        if (cache.get(cur) != null) {
            return cache.get(cur);
        }

        if (cur == amount) {
            cache.put(cur, 0);
            return 0;
        }

        if (cur > amount) {
            cache.put(cur, Integer.MAX_VALUE);
            return Integer.MAX_VALUE;
        }

        int ans = Integer.MAX_VALUE;

        for (int coin : coins) {
            if (coin > amount) {
                continue;
            }
            ans = Math.min(ans, dfs(coins, amount, cur + coin, cache));
        }

        cache.put(cur, ans == Integer.MAX_VALUE ? ans : ans + 1);

        return ans == Integer.MAX_VALUE ? ans : ans + 1;
    }

}
