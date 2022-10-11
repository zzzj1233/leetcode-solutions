package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-10-11 19:45
 */
public class Leet2300 {

    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{1, 3, 5, 7, 9}, 10));
         System.out.println(Arrays.toString(successfulPairs(new int[]{5, 1, 3}, new int[]{1, 2, 3, 4, 5}, 7)));
    }

    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        int N = spells.length;
        int M = potions.length;

        int[] ans = new int[N];

        Arrays.sort(potions);

        for (int i = 0; i < N; i++) {
            int spell = spells[i];

            int expect = (int) Math.ceil(success / (double)spell);

            int idx = binarySearch(potions, expect);

            ans[i] = M - idx;
        }

        return ans;
    }

    public static int binarySearch(int[] potions, int expect) {
        int L = 0;
        int R = potions.length - 1;

        while (L <= R) {
            int mid = L + ((R - L) >> 1);

            if (potions[mid] < expect) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return R + 1;
    }

}
