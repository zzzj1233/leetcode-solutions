package com.zzzj.bit;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-10-12 11:44
 */
public class Leet2275 {

    public static void main(String[] args) {
        System.out.println(largestCombination(new int[]{16, 17, 71, 62, 12, 24, 14}));
        System.out.println(largestCombination(new int[]{8, 8}));
    }

    public static int largestCombination(int[] candidates) {
        int[] bit = new int[32];

        for (int candidate : candidates) {
            for (int i = 0; i < 31; i++) {
                if (((candidate >> i) & 1) == 1) {
                    bit[i]++;
                }
            }
        }

        return Arrays.stream(bit).max().orElse(0);
    }

}
