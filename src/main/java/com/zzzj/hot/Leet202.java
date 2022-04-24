package com.zzzj.hot;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-04-19 17:33
 */
public class Leet202 {

    public static boolean isHappy(int n) {
        Set<Integer> visited = new HashSet<>();

        while (n != 1) {
            int sum = 0;

            while (n != 0) {
                final int mod = n % 10;
                sum += mod * mod;
                n /= 10;
            }

            if (visited.contains(sum)) {
                return false;
            }

            visited.add(sum);
            n = sum;
        }

        return true;
    }

}
