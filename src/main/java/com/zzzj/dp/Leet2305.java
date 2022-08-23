package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-08-23 19:31
 */
public class Leet2305 {


    static int ans;

    public static int distributeCookies(int[] cookies, int k) {
        ans = Integer.MAX_VALUE;
        dfs(cookies, new int[k], 0);
        return ans;
    }

    private static void dfs(int[] cookies, int[] sum, int index) {
        if (index >= cookies.length) {
            ans = Math.min(ans, Arrays.stream(sum).max().orElse(0));
            return;
        }

        int cookie = cookies[index];

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < sum.length; i++) {
            sum[i] += cookie;
            if (sum[i] >= ans) {
                sum[i] -= cookie;
                continue;
            }
            dfs(cookies, sum, index + 1);
            sum[i] -= cookie;
        }

    }

}
