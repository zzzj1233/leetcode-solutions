package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-05-29 19:47
 */
public class Leet35 {

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 2));
    }

    public static int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int mid;

        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

}
