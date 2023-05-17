package com.zzzj.bit;

/**
 * @author zzzj
 * @create 2023-05-12 15:58
 */
public class Leet1720 {

    public static int[] decode(int[] encoded, int first) {

        int N = encoded.length;

        int[] ans = new int[N + 1];

        ans[0] = first;

        for (int i = 0; i < N; i++) {
            ans[i + 1] = encoded[i] ^ ans[i];
        }

        return ans;
    }

}
