package com.zzzj.contest.week398;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2024-05-28 10:55
 */
public class Leet3154 {


    public static void main(String[] args) {

        System.out.println(waysToReachStair(1));

        System.out.println(waysToReachStair(0));

        System.out.println(waysToReachStair(10));

        System.out.println(waysToReachStair(13));

        System.out.println(waysToReachStair(23));

        System.out.println(waysToReachStair(123));

    }

    public static int waysToReachStair(int k) {
        return dfs(
                1,
                k,
                0,
                true,
                new HashMap<>()
        );
    }

    public static int dfs(
            int pos,
            int target,
            int jump,
            boolean can,
            Map<Long, Integer> memo
    ) {

        if (pos - 1 > target)
            return 0;

        int r = 0;

        long key = ((long) pos << 31) | ((long) jump << 16) | (can ? 1 : 0);

        if (memo.containsKey(key))
            return memo.get(key);

        if (pos == target)
            r = 1;
        else if (pos == 0) {
            r = dfs((int) (pos + Math.pow(2, jump)), target, jump + 1, true, memo);
            memo.put(key, r);
            return r;
        }

        if (can)
            r += dfs(pos - 1, target, jump, false, memo);

        r += dfs((int) (pos + Math.pow(2, jump)), target, jump + 1, true, memo);

        memo.put(key, r);
        return r;
    }

}
