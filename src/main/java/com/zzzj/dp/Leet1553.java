package com.zzzj.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-09-28 17:47
 */
public class Leet1553 {

    public static void main(String[] args) {

        System.out.println(minDays(10));

//        System.out.println(minDays(2000000000));
    }

    public static int minDays(int n) {
        return minDays(n, new HashMap<>());
    }

    public static int minDays(int n, Map<Integer, Integer> memo) {
        if (n <= 0)
            return 0;

        if (n <= 2)
            return n;

        if (memo.containsKey(n))
            return memo.get(n);

        int mod = n % 2;

        int case1 = minDays(n / 2) + (mod + 1);

        mod = n % 3;

        int case2 = minDays(n / 3) + (mod + 1);

        int res = Math.min(case1, case2);
        memo.put(n - mod, res);
        return res;
    }

}
