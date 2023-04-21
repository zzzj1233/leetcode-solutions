package com.zzzj.review;

/**
 * @author zzzj
 * @create 2023-03-10 19:51
 */
public class Leet55 {

    public static void main(String[] args) {

        System.out.println(canJump(new int[]{2, 3, 1, 1, 4}));

        System.out.println(canJump(new int[]{3, 2, 1, 0, 4}));

    }

    public static boolean canJump(int[] nums) {

        int N = nums.length;

        int can = nums[0];

        for (int i = 1; i < N; i++) {

            if (can < i) {
                return false;
            }

            int jump = nums[i];

            can = Math.max(can, i + jump);
        }

        return true;
    }

}
