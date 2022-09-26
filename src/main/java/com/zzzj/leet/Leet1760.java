package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-08 18:04
 */
public class Leet1760 {


    public static void main(String[] args) {
        System.out.println(minimumSize(new int[]{9}, 2));
    }

    public static int minimumSize(int[] nums, int maxOperations) {

        int max = Arrays.stream(nums).max().orElse(0);

        int left = 1;
        int right = max;

        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(nums, mid, maxOperations)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    // maxOperations是否可以将nums
    public static boolean check(int[] nums, int expect, int maxOperations) {
        int count = 0;

        for (int num : nums) {
            if (num <= expect) {
                continue;
            }
            count += num / expect;
            if (num % expect == 0) {
                count--;
            }
            if (count > maxOperations) {
                return false;
            }
        }

        return true;
    }

}
