package com.zzzj.str;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-10-23 20:48
 */
public class Leet3303 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(zFunc("aabcaabxaaaz", "aabcaabxaaaz")));

    }

    public static int minStartingIndex(String s, String pattern) {

        int N = s.length();

        int M = pattern.length();

        int[] z = new int[N];

        return -1;
    }

    public static int[] zFunc(String s, String pattern) {

        int N = s.length();

        int M = pattern.length();

        int[] z = new int[M];

        int l = 0;
        int r = 0;

        for (int i = 1; i < M; i++) {

            // 还在窗口内
            if (i < r) {
                int preChar = i - l;
                if (z[preChar] < r) {
                    z[i] = z[preChar];
                    continue;
                }
                while (r + 1 < M && r - l < N && pattern.charAt(r + 1) == s.charAt(r - l))
                    r++;
                z[i] = r;
            } else {
                // 扩充r的窗口
                l = i;
                r = i;
                while (r + 1 < M && r - l < N && pattern.charAt(r + 1) == s.charAt(r - l)) {
                    r++;
                }
                z[i] = r;
            }

        }

        return z;
    }
}
