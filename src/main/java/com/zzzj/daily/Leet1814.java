package com.zzzj.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-01-17 09:46
 */
public class Leet1814 {

    public static void main(String[] args) {

    }

    public static int countNicePairs(int[] nums) {
        // nums[i] -rev(nums[i]) == nums[j] -rev(nums[j])

        final int MOD = (int)1e9 + 7;

        int N = nums.length;

        Map<Long, Integer> count = new HashMap<>(N);

        int ans = 0;

        for (int i = 0; i < N; i++) {
            long value = ((long) nums[i]) - rev(nums[i]) % MOD;
            Integer c = count.get(value);
            if (c != null) {
                ans += c;
            } else {
                c = 0;
            }
            count.put(value, c + 1);
        }

        return ans;
    }

    public static int rev(int num) {
        int result = 0;

        while (num != 0) {
            result = result * 10 + num % 10;
            num /= 10;
        }

        return result;
    }

}
