package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-10-08 17:11
 */
public class Leet1016 {

    public static boolean queryString(String s, int n) {
        for (int i = 1; i <= n; i++) {
            if (!s.contains(Integer.toBinaryString(i))) {
                return false;
            }
        }
        return true;
    }

}
