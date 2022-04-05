package com.zzzj.hot;

/**
 * @author Zzzj
 * @create 2022-04-04 11:39
 */
public class Leet55 {


    public static boolean canJump(int[] nums) {

        int canJump = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > canJump) {
                return false;
            }
            canJump = Math.max(canJump, i + nums[i]);
        }

        return true;
    }


}
