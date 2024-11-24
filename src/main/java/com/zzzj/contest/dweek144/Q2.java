package com.zzzj.contest.dweek144;

import cn.hutool.extra.mail.Mail;

/**
 * @author zzzj
 * @create 2024-11-23 22:33
 */
public class Q2 {

    public static void main(String[] args) {

        System.out.println(shiftDistance("abab", "baba", new int[]{100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{1, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));

        System.out.println(shiftDistance("leet", "code", new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));

    }

    public static long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {

        int N = s.length();

        long ans = 0;

        for (int i = 0; i < N; i++) {
            int c1 = s.charAt(i) - 'a';
            int c2 = t.charAt(i) - 'a';

            if (c1 == c2)
                continue;

            long cost1 = 0;

            while (c1 != c2) {
                cost1 += nextCost[c1];
                c1++;
                if (c1 == 26)
                    c1 = 0;
            }

            c1 = s.charAt(i) - 'a';
            c2 = t.charAt(i) - 'a';

            long cost2 = 0;

            while (c1 != c2) {
                cost2 += previousCost[c1];
                c1--;
                if (c1 < 0)
                    c1 = 25;
            }

            ans += Math.min(cost1, cost2);
        }

        return ans;
    }

}
