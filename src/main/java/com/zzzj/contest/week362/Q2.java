package com.zzzj.contest.week362;

public class Q2 {

    public static void main(String[] args) {

        // 2
        // 1
        // 4
        // 5
        // 2

        System.out.println(isReachableAtTime(2, 1, 4, 5, 2));

        System.out.println(isReachableAtTime(1, 4, 1, 4, 6));

        System.out.println(isReachableAtTime(2, 4, 7, 7, 6));

        System.out.println(isReachableAtTime(1, 2, 1, 2, 1));

        System.out.println(isReachableAtTime(3, 1, 7, 3, 3));

        System.out.println(isReachableAtTime(1, 1, 1, 1, 3));

    }

    public static boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {

        if (sx == fx && sy == fy)
            return t == 0 || t > 1;

        int min = Math.min(Math.abs(fx - sx), Math.abs(fy - sy));

        if (sx + min == fx || fx + min == sx) {
            sx = fx;
            if (sy < fy)
                sy += min;
            else
                fy += min;
        }

        if (sy + min == fy || fy + min == sy) {
            sy = fy;
            if (sx < fx)
                sx += min;
            else
                fx += min;
        }

        return min + Math.abs(fx - sx) + Math.abs(fy - sy) <= t;
    }

}
