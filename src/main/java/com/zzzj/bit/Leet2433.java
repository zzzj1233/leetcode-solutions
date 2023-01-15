package com.zzzj.bit;

public class Leet2433 {

    public static int[] findArray(int[] pref) {

        int N = pref.length;

        int[] ans = new int[N];

        int pre = 0;

        for (int i = 0; i < N; i++) {
            ans[i] = pre ^ pref[i];
            pre ^= ans[i];
        }

        return ans;
    }


}
