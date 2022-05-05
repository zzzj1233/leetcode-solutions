package com.zzzj.hot;


import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-05-05 15:11
 */
public class Leet322 {

    public static void main(String[] args) {
        // [1,2147483647]
        // 2
        // System.out.println(coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(coinChange(new int[]{1, 2147483647}, 2));
    }

    public static int coinChange(int[] coins, int amount) {
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
