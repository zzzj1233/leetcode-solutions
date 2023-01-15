package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-05-21 13:16
 */
public class Leet475 {


    public static void main(String[] args) {
//        System.out.println(findRadius(LeetUtils.convertInts1("[1,2,3]"), LeetUtils.convertInts1("[2]")));
//        System.out.println(findRadius(LeetUtils.convertInts1("[1,2,3,4]"), LeetUtils.convertInts1("[1,4]")));
        // [1,2,3,5,15]
        //[2,30]
        System.out.println(findRadius(LeetUtils.convertInts1("[1,2,3,5,15]"), LeetUtils.convertInts1("[2,30]")));
    }

    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);

        Arrays.sort(heaters);

        int last = houses[houses.length - 1];

        int first = houses[0];

        int left = 0;

        int right = Math.max(last, heaters[heaters.length - 1]);

        while (left < right) {
            int mid = left + ((right - left) >> 1);

            if (check(houses, heaters, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    private static boolean check(int[] houses, int[] heaters, int expect) {
        int N = houses.length;
        int M = heaters.length;

        int j = 0;

        for (int i = 0; i < N; i++) {
            int house = houses[i];
            if (heaters[j] - expect > house) {
                return false;
            }
            while (heaters[j] + expect < house) {
                j++;
                if (j == M) {
                    return false;
                }
            }
            if (heaters[j] - expect > house) {
                return false;
            }
        }

        return true;
    }

}
