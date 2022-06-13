package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-09 14:29
 */
public class Leet2440 {

    public static int[] twoSum(int[] numbers, int target) {

        int l = 0;
        int r = numbers.length - 1;

        while (l < r) {
            int sub = target - numbers[l];
            if (numbers[r] == sub) {
                return new int[]{l, r};
            } else if (numbers[r] > sub) {
                r--;
            } else {
                l++;
            }

        }

        return null;
    }

}
