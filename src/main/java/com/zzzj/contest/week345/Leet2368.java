package com.zzzj.contest.week345;

/**
 * @author zzzj
 * @create 2023-08-01 14:44
 */
public class Leet2368 {

    public static void main(String[] args) {

        System.out.println(doesValidArrayExist(new int[]{1, 1, 0}));

        System.out.println(doesValidArrayExist(new int[]{1, 1}));

        System.out.println(doesValidArrayExist(new int[]{1, 0}));

    }

    public static boolean doesValidArrayExist(int[] derived) {

        int N = derived.length;

        if (N == 1) return false;

        int xor = 0;

        for (int i = 1; i < N - 1; i++) {
            xor ^= derived[i];
        }

        return (derived[0] ^ derived[N - 1]) == xor;
    }

}
