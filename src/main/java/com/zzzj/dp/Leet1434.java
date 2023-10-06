package com.zzzj.dp;

import com.sun.xml.internal.fastinfoset.stax.factory.StAXOutputFactory;
import com.zzzj.leet.LeetUtils;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author zzzj
 * @create 2023-10-01 11:41
 */
public class Leet1434 {

    static final int MOD = 1000000007;

    public static void main(String[] args) {

        System.out.println(numberWays(LeetUtils.convertLists("[[3,4],[4,5],[5]]")));

        System.out.println(numberWays(LeetUtils.convertLists("[[3,5,1],[3,5]]")));

        System.out.println(numberWays(LeetUtils.convertLists("[[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]")));

        System.out.println(numberWays(LeetUtils.convertLists("[[1,2,4,6,7,8,9,11,12,13,14,15,16,18,19,20,23,24,25],[2,5,16],[1,4,5,6,7,8,9,12,15,16,17,19,21,22,24,25],[1,3,6,8,11,12,13,16,17,19,20,22,24,25],[11,12,14,16,18,24],[2,3,4,5,7,8,13,14,15,17,18,21,24],[1,2,6,7,10,11,13,14,16,18,19,21,23],[1,3,6,7,8,9,10,11,12,14,15,16,18,20,21,22,23,24,25],[2,3,4,6,7,10,12,14,15,16,17,21,22,23,24,25]]")));

    }

    public static int numberWays(List<List<Integer>> list) {

        int maxHat = 0;

        Map<Integer, List<Integer>> indexes = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            for (Integer hat : list.get(i)) {
                indexes.computeIfAbsent(hat, integer -> new ArrayList<>()).add(i);
                maxHat = Math.max(maxHat, hat);
            }
        }

        int N = list.size();

        int max = 1 << N;

        int[][] dp = new int[maxHat + 1][max];

        dp[0][0] = 1;

        for (int i = 1; i < dp.length; i++) {

            dp[i] = Arrays.copyOfRange(dp[i - 1], 0, max);

            if (indexes.get(i) == null) {
                continue;
            }

            // available indexes
            for (Integer index : indexes.get(i)) {

                int bit = 1 << index;

                for (int stat = 0; stat < max; stat++) {

                    if ((stat & bit) != 0)
                        continue;

                    dp[i][stat | bit] += dp[i - 1][stat] % MOD;
                    dp[i][stat | bit] %= MOD;
                }

            }

        }

        return dp[maxHat][max - 1];
    }

    public static int dfs(long stat, List<List<Integer>> list, Map<Long, Integer> memo) {

        int index = Long.bitCount(stat);

        if (index >= list.size())
            return 1;

        if (memo.containsKey(stat))
            return memo.get(stat);

        int ret = 0;

        List<Integer> hats = list.get(index);

        for (Integer hat : hats) {
            if ((stat & (1L << hat)) != 0)
                continue;
            ret += dfs(stat | (1L << hat), list, memo) % MOD;
            ret %= MOD;
        }

        memo.put(stat, ret);
        return ret;
    }

    public static int stat_dp(List<List<Integer>> list) {

        Map<Long, Integer> dp = new HashMap<>();

        Map<Long, Integer> roll = new HashMap<>();

        int N = list.size();

        roll.put(0L, 1);

        for (int i = 1; i <= N; i++) {

            List<Integer> hats = list.get(i - 1);

            long hatStat = 0;

            for (Integer hat : hats)
                hatStat |= 1L << hat;

            for (Entry<Long, Integer> entry : roll.entrySet()) {

                long stat = entry.getKey();

                long s = (hatStat ^ stat) & hatStat;

                long low;

                while ((low = lowbit(s)) != 0) {
                    long key = stat | low;
                    dp.put(key, dp.getOrDefault(key, 0) + entry.getValue());
                    s ^= low;
                }

            }

            roll = dp;
            dp = new HashMap<>();
        }

        System.out.println(roll.keySet().size());

        return roll.values().stream().mapToInt(value -> value).sum();
    }

    public static long lowbit(long x) {
        return x & -x;
    }
}
