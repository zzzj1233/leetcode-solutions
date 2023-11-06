package com.zzzj.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-09-28 17:47
 */
public class Leet1553 {

    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    public int minDays(int n) {
        if (n <= 1) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        memo.put(n, Math.min(n % 2 + 1 + minDays(n / 2), n % 3 + 1 + minDays(n / 3)));
        return memo.get(n);
    }

}
