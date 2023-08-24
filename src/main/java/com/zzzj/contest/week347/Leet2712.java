package com.zzzj.contest.week347;

public class Leet2712 {

    public static long minimumCost(String s) {

        return -1;
    }

    public static long greedy(String s, char expect) {

        boolean reversal = false;

        int N = s.length();

        int start = -1;

        for (int i = 0; i < N; i++) {
            if (s.charAt(i) != expect) {

                if (start == -1) {
                    start = i;
                }

            } else {
                // [ start ~ i - 1 ] 这段不是期望的数字
                if (start != -1) {

                }

            }
        }

        return -1;
    }

}
