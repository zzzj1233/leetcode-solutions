package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-03-11 15:04
 */
public class Leet455 {

    public static void main(String[] args) {

    }

    // [10,9,8,7]
    // [5,6,7,8]
    public static int findContentChildren(int[] g, int[] s) {
        int ans = 0;

        Arrays.sort(g);

        for (int i = 0; i < Math.min(s.length, g.length); i++) {
            if (g[ans] > s[i]) {
                continue;
            }
            ans++;
        }

        return ans;
    }

}
