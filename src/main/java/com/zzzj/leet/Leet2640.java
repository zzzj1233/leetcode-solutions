package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-27 20:19
 */
public class Leet2640 {

    public static int[] swapNumbers(int[] numbers) {
        int x = numbers[0];
        int y = numbers[1];

        x ^= y;

        y = x ^ y;

        x ^= y;

        return new int[]{x, y};
    }

}
