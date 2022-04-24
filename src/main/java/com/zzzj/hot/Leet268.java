package com.zzzj.hot;

/**
 * @author zzzj
 * @create 2022-04-24 11:49
 */
public class Leet268 {

    // N = 3
    // nums should be 0 1 2 [3]
    // 3 is the missing num
    public static int missingNumber(int[] nums) {
        boolean[] record = new boolean[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            record[nums[i]] = true;
        }

        for (int i = 0; i < record.length; i++) {
            if (!record[i]) {
                return i;
            }
        }

        return -1;
    }

}
