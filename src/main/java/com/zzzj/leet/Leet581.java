package com.zzzj.leet;


import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-05-23 18:43
 */
public class Leet581 {

    // [2,6,4,8,10,9,15]
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            final int[] arr = ArrayUtil.generateArray(1000, 0, 10);
            if (findUnsortedSubarray(arr) != right(arr)) {
                System.out.println("Right count = " + i);
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                return;
            }
        }
    }

    // [2,3,3,2,4]
    public static int findUnsortedSubarray(int[] nums) {
        int orderedMax = 0;
        int max = 0;

        int i = 1;

        int end = nums.length - 1;

        while (i < nums.length && nums[i] >= nums[i - 1]) {
            i++;
            orderedMax++;
            max++;
        }

        if (i == nums.length) {
            return 0;
        }

        // i就是第一个无序的位置
        while (i < nums.length) {
            // 缩小max的范围
            if (orderedMax >= 0 && nums[i] < nums[orderedMax]) {
                int j = orderedMax - 1;
                while (j >= 0 && nums[j] > nums[i]) {
                    j--;
                }
                orderedMax = j;
            }

            if (nums[i] < nums[max]) {
                end = i + 1;
            } else {
                max = i;
            }

            i++;
        }

        return (end - (orderedMax + 1));
    }

    public static int right(int[] nums) {
        //初始化
        int len = nums.length;
        int min = nums[len - 1];
        int max = nums[0];
        int begin = 0, end = -1;
        //遍历
        for (int i = 0; i < len; i++) {
            if (nums[i] < max) {      //从左到右维持最大值，寻找右边界end
                end = i;
            } else {
                max = nums[i];
            }

            if (nums[len - i - 1] > min) {    //从右到左维持最小值，寻找左边界begin
                begin = len - i - 1;
            } else {
                min = nums[len - i - 1];
            }
        }
        return end - begin + 1;
    }


}
