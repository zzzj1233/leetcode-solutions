package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-05-25 14:13
 */
public class Leet455 {

    public static void main(String[] args) {
        System.out.println(findContentChildren(new int[]{1, 4}, new int[]{2, 3, 4}));
        System.out.println(findContentChildren(new int[]{1, 2, 3}, new int[]{11}));
        System.out.println(findContentChildren(new int[]{1, 2}, new int[]{1, 3, 4}));
    }

    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int j = 0;
        int i = 0;

        for (; i < s.length && j < g.length; i++) {
            if (s[i] >= g[j]) {
                j++;
            }
        }
        return j;
    }

}
