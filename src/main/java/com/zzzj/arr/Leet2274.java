package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-10-12 11:55
 */
public class Leet2274 {

    public static int maxConsecutive(int bottom, int top, int[] special) {

        Arrays.sort(special);

        int ans = 0;

        int N = special.length;

        int pre = bottom;

        for (int i = 0; i < N; i++) {
            int rest = special[i];
            ans = Math.max(ans, rest - pre);
            pre = rest + 1;
        }

        ans = Math.max(ans, top - pre);

        return ans;
    }

}
