package com.zzzj.arr;

import java.util.Arrays;

public class Leet2419 {


    public static long dividePlayers(int[] skill) {

        int N = skill.length;

        Arrays.sort(skill);

        int half = N / 2;

        int expect = skill[0] + skill[N - 1];

        long sum = 0;

        for (int i = 0; i < half; i++) {
            if (skill[i] + skill[N - 1 - i] != expect) {
                return -1;
            }
            sum += (long) skill[i] * skill[N - 1 - i];
        }

        return sum;
    }


}
