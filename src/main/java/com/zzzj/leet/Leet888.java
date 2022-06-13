package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-09 15:31
 */
public class Leet888 {

    public static void main(String[] args) {
        // [35,17,4,24,10]
        // [63,21]
        System.out.println(Arrays.toString(fairCandySwap(new int[]{35, 17, 4, 24, 10}, new int[]{63, 21})));
    }

    public static int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        Arrays.sort(aliceSizes);
        Arrays.sort(bobSizes);

        int sum1 = Arrays.stream(aliceSizes).sum();
        int sum2 = Arrays.stream(bobSizes).sum();

        int[] bigger = sum1 > sum2 ? aliceSizes : bobSizes;
        int[] litter = sum1 > sum2 ? bobSizes : aliceSizes;

        int sub = sum1 > sum2 ? sum1 - sum2 : sum2 - sum1;

        for (int i = bigger.length - 1; i >= 0; i--) {
            int tar = bigger[i] - sub / 2;
            if (Arrays.binarySearch(litter, tar) > -1) {
                if (bigger == aliceSizes) {
                    return new int[]{bigger[i], tar};
                } else {
                    return new int[]{tar, bigger[i]};
                }
            }
        }


        return null;
    }

}
