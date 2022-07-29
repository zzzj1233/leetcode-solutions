package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-07-29 11:41
 */
public class Leet1710 {

    public static void main(String[] args) {
        System.out.println(maximumUnits(LeetUtils.convertInts("[[1,3],[2,2],[3,1]]"), 4));
    }

    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (o1, o2) -> o2[1] - o1[1]);

        int ans = 0;

        for (int i = 0; i < boxTypes.length && truckSize > 0; i++) {
            int[] boxType = boxTypes[i];
            int count = boxType[0];
            int size = boxType[1];

            int can = Math.min(truckSize, count);

            ans += can * size;

            truckSize -= can;
        }

        return ans;
    }

}
