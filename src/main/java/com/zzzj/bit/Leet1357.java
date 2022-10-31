package com.zzzj.bit;

import java.util.TreeSet;

/**
 * @author zzzj
 * @create 2022-10-28 19:42
 */
public class Leet1357 {


    public static void main(String[] args) {
        System.out.println(numTimesAllBlue(new int[]{3, 2, 4, 1, 5}));
    }

    public static int numTimesAllBlue(int[] flips) {
        int N = flips.length;

        TreeSet<Integer> set = new TreeSet<>();

        for (int i = 1; i <= N; i++) {
            set.add(i);
        }

        int ans = 0;

        int max = 0;

        for (int i = 0; i < N; i++) {
            int item = flips[i];

            max = Math.max(max, item);

            set.remove(item);

            if (set.floor(max) == null) {
                ans++;
            }
        }

        return ans;
    }

}
