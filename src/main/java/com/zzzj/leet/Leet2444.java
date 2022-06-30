package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-27 15:48
 */
public class Leet2444 {

    public static int singleNumber(int[] nums) {
        int[] bucket = new int[32];

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < 32; j++) {
                if ((nums[i] >> j & 1) == 1) {
                    bucket[j]++;
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < 32; i++) {
            if (bucket[i] % 3 != 0) {
                ans |= 1 << i;
            }
        }

        return ans;
    }

}
