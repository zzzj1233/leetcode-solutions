package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2022-11-03 15:15
 */
public class Leet397 {


    public static int integerReplacement(int n) {
        return dfs(n);
    }

    public static int dfs(long cur) {
        if (cur == 1) {
            return 0;
        }

        if (cur % 2 == 0) {
            return 1 + dfs(cur / 2);
        }

        return 1 + Math.min(dfs((cur - 1)), dfs((cur + 1)));
    }

}
