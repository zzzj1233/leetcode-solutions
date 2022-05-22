package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-05-21 13:16
 */
public class Leet475 {

    public static void main(String[] args) {
        System.out.println(findRadius(new int[]{100, 5, 7, 9, 11}, new int[]{1, 5}));
    }

    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);

        int ans = 0;

        int side = heaters.length;

        for (int i = heaters.length - 1; i >= 0; i--) {
            int heaterIndex = heaters[i];

            if (houses[heaterIndex - 1] + ans < side) {
                ans += side - houses[heaterIndex - 1] + ans;
            }

        }

        if (-heaters[heaters.length - 1] > ans){
            ans = heaters[heaters.length - 1];
        }

        return ans;
    }

}
