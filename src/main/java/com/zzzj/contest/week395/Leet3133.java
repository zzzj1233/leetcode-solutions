package com.zzzj.contest.week395;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2024-06-06 12:08
 */
public class Leet3133 {

    public static void main(String[] args) {

        System.out.println(minEnd(6715154, 7193485));

        System.out.println(minEnd(3, 4));

        System.out.println(minEnd(2, 7));

        System.out.println(minEnd(234234, 56645234));

        System.out.println(LeetUtils.toBinaryString(415789729677L, 64));

        System.out.println(LeetUtils.toBinaryString(55012476815L, 64));
    }

    public static long minEnd(int n, long x) {

        int[] cnt = new int[64];

        for (int i = 0; i < 64; i++) {
            if (i > 0)
                cnt[i] = cnt[i - 1];
            if ((x & (1L << i)) == 0)
                cnt[i]++;
        }

        int left = n - 1;

        long ans = x;

        for (int i = 62; i >= 0; i--) {

            if ((x & (1L << i)) == 0) {

                if (Math.pow(2, cnt[i] - 1) > left) {
                    continue;
                }

                left -= Math.pow(2, cnt[i] - 1);

                ans |= 1L << i;
            }

        }

        return ans;
    }

}
