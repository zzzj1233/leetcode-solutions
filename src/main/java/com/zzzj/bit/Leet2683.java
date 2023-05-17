package com.zzzj.bit;

/**
 * @author zzzj
 * @create 2023-05-17 11:39
 */
public class Leet2683 {

    public static void main(String[] args) {
        System.out.println(doesValidArrayExist(new int[]{1, 1, 0}));
        System.out.println(doesValidArrayExist(new int[]{1, 1}));
        System.out.println(doesValidArrayExist(new int[]{1, 0}));
    }

    public static boolean doesValidArrayExist(int[] derived) {

        int N = derived.length;

        if (N == 1) {
            return derived[0] == 0;
        }

        int xor = 0;

        for (int i = 0; i < N - 1; i++) {
            xor ^= derived[i];
        }

        return xor == derived[N - 1];
    }

}
