package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-06-07 21:04
 */
public class Leet852 {

    public static int peakIndexInMountainArray(int[] arr) {
        int l = 0;
        int r = arr.length - 1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);


            if (mid + 1 >= arr.length) {
                r = mid - 1;
                continue;
            }

            if (mid - 1 < 0) {
                l = mid + 1;
                continue;
            }

            if (arr[mid - 1] < arr[mid] && arr[mid + 1] < arr[mid]) {
                return mid;
            } else if (arr[mid - 1] < arr[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }

}
