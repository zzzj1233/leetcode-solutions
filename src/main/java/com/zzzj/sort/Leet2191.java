package com.zzzj.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2022-09-22 14:56
 */
public class Leet2191 {

    // [9,8,7,6,5,4,3,2,1,0]
    // [0,1,2,3,4,5,6,7,8,9]
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(sortJumbled(new int[]{8, 9, 4, 0, 2, 1, 3, 5, 7, 6}, new int[]{991, 338, 38})));
        System.out.println(Arrays.toString(sortJumbled(new int[]{7, 9, 4, 1, 0, 3, 8, 6, 2, 5}, new int[]{47799, 19021, 162535, 454, 95, 51890378, 404})));
    }

    // [7,9,4,1,0,3,8,6,2,5]

    // [47799,19021,162535,454,95,51890378,404]

    // 404 & 95
    // 7
    public static int[] sortJumbled(int[] mapping, int[] nums) {

        int N = nums.length;

        int[][] temp = new int[N][2];

        for (int i = 0; i < N; i++) {
            int num = nums[i];

            int weightNum = 0;

            StringBuilder numStr = new StringBuilder(String.valueOf(num));

            for (int j = 0; j < numStr.length(); j++) {
                int numBit = Character.digit(numStr.charAt(j), 10);
                weightNum = weightNum * 10 + mapping[numBit];
            }

            temp[i][0] = nums[i];
            temp[i][1] = weightNum;
        }

        Arrays.sort(temp, Comparator.comparingInt(o -> o[1]));

        for (int i = 0; i < N; i++) {
            nums[i] = temp[i][0];
        }

        return nums;
    }

}
