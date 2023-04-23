package com.zzzj.bit;

/**
 * @author zzzj
 * @create 2023-04-23 15:49
 */
public class Leet2568 {

    public static void main(String[] args) {
        System.out.println(minImpossibleOR(new int[]{2, 1}));
    }

    public static int minImpossibleOR(int[] nums) {

        int or = 0;

        for (int num : nums) {
            or |= num;
        }

        for (int i = 0; i < 32; i++) {
            if ((or >> i & 1) == 0) {
                return (or + 1) & (~or);
            }
        }

        return -1;
    }

}
