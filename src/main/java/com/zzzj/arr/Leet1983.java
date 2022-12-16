package com.zzzj.arr;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-11-24 17:08
 */
public class Leet1983 {


    public static int widestPairOfIndices(int[] nums1, int[] nums2) {

        int N = nums1.length;

        int[] preSum1 = new int[N + 1];
        int[] preSum2 = new int[N + 1];

        int ans = 0;

        Map<Integer, Integer> position = new HashMap<>();

        position.put(0, 0);

        for (int i = 1; i <= N; i++) {
            preSum1[i] = nums1[i - 1] + preSum1[i - 1];

            preSum2[i] = nums2[i - 1] + preSum2[i - 1];

            int sub = preSum1[i] - preSum2[i];

            if (position.containsKey(sub)) {
                ans = Math.max(ans, i - position.get(sub) + 1);
            } else {
                position.put(sub, i + 1);
            }
        }

        return ans;
    }


}
