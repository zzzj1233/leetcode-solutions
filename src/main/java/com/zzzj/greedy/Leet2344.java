package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-11-01 12:07
 */
public class Leet2344 {

    public static void main(String[] args) {

        System.out.println(minOperations(new int[]{3, 2, 6, 2, 35, 5, 35, 2, 5, 8, 7, 3, 4}, new int[]{105, 70, 70, 175, 105, 105, 105}));
//
//        System.out.println(minOperations(new int[]{2, 3, 2, 4, 3}, new int[]{9, 6, 9, 3, 15}));
//
//        System.out.println(minOperations(new int[]{4, 3, 6}, new int[]{8, 2, 6, 10}));

    }

    public static int minOperations(int[] nums, int[] numsDivide) {

        int gcd = findArrayGCD(numsDivide);

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (gcd % nums[i] == 0)
                return i;
        }

        return -1;
    }

    public static int findArrayGCD(int[] numbers) {
        int result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = findGCD(result, numbers[i]);
        }
        return result;
    }

    // 使用欧几里德算法计算两个整数的最小公约数
    public static int findGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return findGCD(b, a % b);
    }

}
