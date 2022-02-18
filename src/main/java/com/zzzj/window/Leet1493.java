package com.zzzj.window;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.*;

/**
 * @author zzzj
 * @create 2021-12-24 17:49
 */
public class Leet1493 {

    public static void main(String[] args) {
        final Random random = LeetUtils.random;
        int length = random.nextInt(15) + 5;

        final int[] arr = ArrayUtil.generateArray(length, 0, 2);

        System.out.println(Arrays.toString(arr));

        System.out.println(longestSubarray(arr));

    }

    public static int longestSubarray(int[] nums) {
        boolean hasOne = false;
        boolean hasZero = false;

        boolean deleted = false;

        int l = 0;
        int r = 0;

        int window = 0;
        int ans = 0;

        while (r < nums.length) {
            if (nums[r] == 0) {
                if (deleted) {
                    // 挪动L,直到L跳过一个0
                    while (l < nums.length && nums[l] == 1) {
                        l++;
                    }
                    l++;
                    deleted = l <= r;
                } else {
                    deleted = true;
                }
                hasZero = true;
            } else {
                hasOne = true;
            }
            ans = Math.max(ans, r - l + (deleted ? 0 : 1));
            r++;
        }

        if (!hasOne) {
            return 0;
        }

        return hasZero ? ans : Math.max(nums.length - 1, 0);
    }

}
