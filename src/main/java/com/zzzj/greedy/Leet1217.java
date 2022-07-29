package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2022-07-29 11:53
 */
public class Leet1217 {

    public static int minCostToMoveChips(int[] position) {

        int odd = 0;
        int even = 0;

        for (int i : position) {
            if (i % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        return Math.min(odd, even);
    }

}
