package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-24 18:19
 */
public class Leet633 {

    public static void main(String[] args) {

    }

    public static boolean judgeSquareSum(int c) {
        long low = 0;
        long high = (int) Math.sqrt(c);

        while (low <= high) {
            long sum = low * low + high * high;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                high--;
            } else {
                low++;
            }
        }

        return false;
    }

}
