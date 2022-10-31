package com.zzzj.greedy;

public class Leet1689 {

    public static int minPartitions(String n) {

        int ans = 0;

        for (int i = 0; i < n.length(); i++) {
            ans = Math.max(ans, Character.digit(n.charAt(i), 10));
        }

        return ans;
    }

}
