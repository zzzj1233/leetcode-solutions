package com.zzzj.daily;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-10-16 10:10
 */
public class Leet260 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(singleNumber(new int[]{1, 2, 1, 3, 2, 5})));

    }

    public static int[] singleNumber(int[] nums) {

        int xor = 0;

        for (int num : nums) xor ^= num;

        int xor2 = 0;

        int lowbit = lowbit(xor);

        for (int num : nums) {
            if ((num & lowbit) != 0)
                xor2 ^= num;
        }

        int firstNum = xor ^ xor2;

        return new int[]{firstNum, xor ^ firstNum};
    }

    public static int lowbit(int x) {
        return x & (-x);
    }

}
