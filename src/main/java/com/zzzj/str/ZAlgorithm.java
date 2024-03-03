package com.zzzj.str;

import java.util.Arrays;

public class ZAlgorithm {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(Z("aabcaabxaaaz")));

    }

    public static int[] Z(String str) {

        int N = str.length();

        int[] z = new int[N];

        z[0] = N;

        int l = 0;
        int r = 0;

        for (int k = 1; k < N; k++) {

            // in z-box
            if (k <= r) {

                // 找到前缀对应的匹配串
                int prev = k - l;

                // 如果没有超出窗口
                if (z[prev] + k < r) {
                    z[k] = z[prev];
                } else {
                    l = k;

                    while (r < N && str.charAt(r) == str.charAt(r - l)) {
                        r++;
                    }

                    z[k] = r - l;
                    r--;
                }

            } else {
                l = k;
                r = k;

                while (r < N && str.charAt(r) == str.charAt(r - l)) {
                    r++;
                }

                z[k] = r - l;
                r--;
            }
        }

        return z;
    }

}
