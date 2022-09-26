package com.zzzj.greedy;


import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-16 14:58
 */
public class Leet1561 {

    /**
     * 每次拿3个,只能打第二大的硬币,求最多能拿多少个硬币
     */
    public static int maxCoins(int[] piles) {
        Arrays.sort(piles);

        int ans = 0;

        int N = piles.length;

        int LEFT = 0;

        int RIGHT = N - 1;

        while (LEFT < RIGHT) {
            ans += piles[RIGHT - 1];
            RIGHT -= 2;
            LEFT += 1;
        }

        return ans;
    }


}
