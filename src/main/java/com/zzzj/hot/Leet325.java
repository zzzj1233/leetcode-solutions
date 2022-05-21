package com.zzzj.hot;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-05-11 15:32
 */
public class Leet325 {

    public static void main(String[] args) {
        System.out.println(maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
        System.out.println(maxSubArrayLen(new int[]{-2, -1, 2, 1}, 1));
    }

    public static int maxSubArrayLen(int[] nums, int k) {
        int N = nums.length;

        long[] preSum = new long[N + 1];

        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        Map<Long, Integer> map = new HashMap<>(N);

        for (int i = 0; i < preSum.length; i++) {
            map.putIfAbsent(preSum[i], i);
        }

        int ans = 0;

        for (int i = preSum.length - 1; i >= 0; i--) {
            long sum = preSum[i];
            long sub = sum - k;
            Integer subIdx = map.get(sub);
            if (subIdx != null) {
                ans = Math.max(ans, i - subIdx);
                if (subIdx == 0) {
                    return ans;
                }
            }
        }

        return ans;
    }

}
