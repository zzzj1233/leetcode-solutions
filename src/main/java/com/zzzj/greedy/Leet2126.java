package com.zzzj.greedy;

import java.util.Arrays;

public class Leet2126 {

    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {

        Arrays.sort(asteroids);

        int cur = mass;

        int N = asteroids.length;

        for (int i = 0; i < N; i++) {
            if (cur >= asteroids[i]) {
                cur += asteroids[i];
            } else {
                return false;
            }
        }

        return true;
    }

}
