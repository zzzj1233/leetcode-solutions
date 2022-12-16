package com.zzzj.sort;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-11-23 11:09
 */
public class Leet2165 {

    public static void main(String[] args) {
        System.out.println(smallestNumber(310));
        System.out.println(smallestNumber(-7605));
    }

    public static long smallestNumber(long num) {
        // 1. 可能为负数
        // 2. 第一个数不能为0

        // -123 -> -321
        // 213 -> 123

        // 30013 -> 10033

        if (num < 0) {
            char[] chars = String.valueOf(-num).toCharArray();

            Arrays.sort(chars);

            reverse(chars);

            return -Long.parseLong(String.valueOf(chars));
        } else {
            char[] chars = String.valueOf(num).toCharArray();

            Arrays.sort(chars);

            int index = 0;

            int N = chars.length;

            while (index < N && chars[index] == '0') {
                index++;
            }

            if (index >= N) {
                return 0;
            }

            char temp = chars[index];
            chars[index] = '0';
            chars[0] = temp;

            return Long.parseLong(String.valueOf(chars));
        }
    }

    public static void reverse(char[] array) {
        int i = 0;
        int j = array.length - 1;
        while (j > i) {
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            j--;
            i++;
        }
    }


}
