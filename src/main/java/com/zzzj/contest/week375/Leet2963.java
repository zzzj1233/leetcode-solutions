package com.zzzj.contest.week375;

import java.util.HashMap;
import java.util.Map;

public class Leet2963 {


    public static int numberOfGoodPartitions(int[] nums) {

        int N = nums.length;

        Map<Integer, Integer> rec = new HashMap<>(N);

        for (int i = 0; i < N; i++)
            rec.put(nums[i], i);

        int cnt = 0;

        int maxRight = 0;

        for (int i = 0; i < N; i++) {
            maxRight = Math.max(
                    maxRight,
                    rec.get(nums[i])
            );
            if (maxRight == i)
                cnt++;
        }

        int ans = 1;

        final int MOD = 1000000007;

        for (int i = 1; i < cnt; i++) {
             ans = (ans << 1) % MOD;
        }

        return ans;
    }

}
