package com.zzzj.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-06-16 11:03
 */
public class Leet464 {

    public static void main(String[] args) {
        // 5
        // 50
        System.out.println(canIWin(10, 11));
    }


    public static boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if ((maxChoosableInteger * (1 + maxChoosableInteger) / 2) < desiredTotal) {
            return false;
        }
        return dfs(maxChoosableInteger, desiredTotal, 0, 0, new HashMap<>(18));
    }

    public static boolean dfs(int maxChoosableInteger, int desiredTotal, int state, int total, Map<Integer, Boolean> cache) {
        if (cache.containsKey(state)) {
            return cache.get(state);
        }
        int remain = desiredTotal - total;

        for (int i = remain; i <= maxChoosableInteger; i++) {
            if (((state >> i) & 1) == 0) {
                cache.put(state, true);
                return true;
            }
        }

        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (((state >> i) & 1) == 1) {
                continue;
            }
            if (!dfs(maxChoosableInteger, desiredTotal, state | 1 << i, total + i, cache)) {
                cache.put(state, true);
                return true;
            }
        }

        cache.put(state, false);
        return false;
    }

}
