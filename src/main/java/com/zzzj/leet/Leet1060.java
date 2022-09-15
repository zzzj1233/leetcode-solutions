package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-09-07 20:27
 */
public class Leet1060 {

    public static void main(String[] args) {
        System.out.println(missingElement(new int[]{4, 7, 9, 10}, 3));
    }

    public static int missingElement(int[] nums, int k) {
        int target = k;

        int N = nums.length;

        for (int i = 1; i < N; i++) {
            int sub = nums[i] - nums[i - 1] - 1;
            if (sub == 0) {
                continue;
            }
            // 在这个区间内
            if (target <= sub) {
                return nums[i - 1] + target;
            } else {
                target -= sub;
            }
        }

        return -1;
    }


}
