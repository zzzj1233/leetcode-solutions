package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-28 12:16
 */
public class Leet367 {

    public static boolean isPerfectSquare(int num) {

        int left = 1;
        int right = num;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);

            int div = num / mid;

            if (div == mid) {
                if (num % div == 0) {
                    return true;
                }
                left = mid + 1;
            } else if (div < mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return false;
    }

}
