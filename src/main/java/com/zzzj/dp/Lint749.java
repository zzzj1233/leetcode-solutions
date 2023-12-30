package com.zzzj.dp;

public class Lint749 {

    public static void main(String[] args) {
        System.out.println(isBuild(10));
        System.out.println(isBuild(5));
        System.out.println(isBuild(13));
    }

    public static String isBuild(int x) {

        boolean[] f = new boolean[x + 1];

        f[0] = true;

        for (int i = 3; i <= x; i++) {
            f[i] |= f[i - 3] | (i >= 7 && f[i - 7]);
        }

        return f[x] ? "YES" : "NO";
    }

}
