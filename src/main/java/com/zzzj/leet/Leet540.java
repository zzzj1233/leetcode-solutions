package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-09 14:47
 */
public class Leet540 {

    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
    }

    public static int singleNonDuplicate(int[] nums) {

        int N = nums.length;

        int l = 0;
        int r = N - 1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);

            int val = nums[mid];

            boolean leftSame = mid - 1 >= 0 && nums[mid - 1] == val;
            boolean rightSame = mid + 1 < N && nums[mid + 1] == val;

            int right;

            if (leftSame) {
                right = mid;
            } else if (rightSame) {
                right = mid + 1;
            } else {
                return val;
            }

            if ((right + 1) % 2 == 0) {
                l = right + 1;
            } else {
                r = right - 1;
            }
        }

        return -1;
    }

}
