package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2022-12-16 15:52
 */
public class Leet153 {

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        System.out.println(findMin(new int[]{3, 4, 5, 1, 2}));
        System.out.println(findMin(new int[]{11, 13, 15, 17}));
    }

    public static int findMin(int[] nums) {

        // 1. 如果mid > right , 那么ans一定在右侧


        // 4, 5, 6, 7, 0, 1, 2  , [mid] = 7
        // 3, 4, 5, 1, 2 , [mid] = 5

        // 因为1,2,3,4,5
        // 按照顺时针旋转,不可能成为 1,2,5,4,3
        // rotate(1) = 5 1 2 3 4
        // rotate(2) = 4 5 1 2 3
        // rotate(3) = 3 4 5 1 2
        // rotate(4) = 2 3 4 5 1

        // 2. 如果mid < right , 那么ans一定在左侧,或者mid就是ans

        int left = 0;

        int right = nums.length - 1;

        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[right];
    }

}
