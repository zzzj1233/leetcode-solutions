package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-05-09 16:19
 */
public class Leet280 {

    public static void main(String[] args) {
        final int[] arr = {3, 5, 2, 1, 6, 4};

        wiggleSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void wiggleSort(int[] nums) {
        Arrays.sort(nums);

        // 摆动排序
        int r = nums.length - 1;

        int l = 1;

        // [3,5,2,1,6,4]

        // [1,6,2,5,3,4]

        int temp = 0;
        while (l < nums.length) {
            if (l % 2 != 0) {
                temp = nums[l];
                nums[l] = nums[r--];
            } else {
                int temp2 = nums[l];
                nums[l] = temp;
                temp = temp2;
            }
            l++;
        }

    }

}
