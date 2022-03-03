package com.zzzj.backtracking;

/**
 * @author Zzzj
 * @create 2022-03-03 22:45
 */
public class Leet357 {

    public static void main(String[] args) {
        System.out.println(countNumbersWithUniqueDigits(3));
    }

    public static int ans;

    public static int[] considerable = {9, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    public static int countNumbersWithUniqueDigits(int n) {
        ans = 1;


        // i = 几位数
        for (int i = n - 1; i >= 0; i--) {

            int val = 1;

            for (int j = i; j >= 0; j--) {

                val *= considerable[j];

            }

            ans += val;
        }


        return ans;
    }


}
