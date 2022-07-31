package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-07-31 12:50
 */
public class Leet324 {

    public static void main(String[] args) {
//        wiggleSort(new int[]{1, 5, 1, 1, 6, 4});
        //
        // 1 1 1 4 5 6
        // 1,4,1,5,1,6

        // 1 1 2 2 3 3
        // 1 3 2 3 2 1
        wiggleSort(new int[]{1, 3, 2, 2, 3, 1});
    }

    public static void wiggleSort(int[] nums) {
        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;

        // 1 < 2 > 3 < 4 > 5 < 6
        // 1 1 2 2 3 3
        // 2 3 1 3 1 2

        //

    }


}
