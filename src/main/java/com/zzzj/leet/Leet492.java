package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-10-25 14:19
 */
public class Leet492 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(constructRectangle(18)));
    }

    public static int[] constructRectangle(int area) {
        int sqrt = (int) Math.sqrt(area);

        while (area % sqrt != 0) {
            sqrt -= 1;
        }

        return new int[]{area / sqrt, sqrt};
    }

}
