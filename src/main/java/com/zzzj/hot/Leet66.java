package com.zzzj.hot;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-04-06 11:38
 */
public class Leet66 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(plusOne(new int[]{4, 3, 2, 1})));
        System.out.println(Arrays.toString(plusOne(new int[]{1, 9, 0, 9})));
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9, 9})));
    }

    public static int[] plusOne(int[] digits) {
        int add = 1;

        for (int i = digits.length - 1; i >= 0 && add > 0; i--) {
            // 需要进位
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] += 1;
                add -= 1;
            }
        }

        if (add > 0) {
            int[] ans = new int[digits.length + 1];
            ans[0] = 1;
            System.arraycopy(digits, 0, ans, 1, digits.length);
            return ans;
        } else {
            return digits;
        }
    }

}
