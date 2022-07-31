package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-07-30 15:09
 */
public class Leet246 {


    private static final Integer[] mapping = new Integer[10];

    public static boolean isStrobogrammatic(String num) {
        mapping[0] = 0;
        mapping[1] = 1;
        mapping[8] = 8;
        mapping[6] = 9;
        mapping[9] = 6;

        int left = 0;
        int right = num.length() - 1;

        while (left <= right) {
            int leftVal = Character.digit(num.charAt(left), 10);
            int rightVal = Character.digit(num.charAt(right), 10);

            if (mapping[leftVal] != rightVal || mapping[rightVal] != leftVal) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

}
