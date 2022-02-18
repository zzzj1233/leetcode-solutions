package com.zzzj.dpoint;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-12-21 16:34
 */
public class Leet27 {

    public static void main(String[] args) {
        final int[] arr = {3, 2, 2, 3};

        System.out.println(removeElement(arr, 3));

        System.out.println(Arrays.toString(arr));
    }

    // 0 1 2 2 3 0 4 2 , 2
    public static int removeElement(int[] nums, int val) {
        int i = 0;
        int j = nums.length - 1;
        int newLen = nums.length;

        while (i < j) {
            if (nums[i] == val) {
                nums[i] = nums[j];
                j--;
                newLen--;
            } else {
                i++;
            }
        }

        return newLen;
    }

}
