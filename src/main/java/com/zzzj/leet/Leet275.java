package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-05-10 19:30
 */
public class Leet275 {

    public static void main(String[] args) {
        int[] arr = {0, 1, 3, 5, 6};

        System.out.println(hIndex(arr));
    }

    public static int hIndex(int[] citations) {
        int N = citations.length;

        int l = 0;
        int r = N;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            int index = binarySearch(citations, mid);
            int count = N - index;

            // 不足
            if (count < mid) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }

        }

        return l - 1;
    }

    public static int binarySearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }

}
