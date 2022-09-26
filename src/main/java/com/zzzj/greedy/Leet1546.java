package com.zzzj.greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-09-16 15:08
 */
public class Leet1546 {

    public static void main(String[] args) {
        System.out.println(maxNonOverlapping(new int[]{1, 1, 1, 1, 1}, 2));
        System.out.println(maxNonOverlapping(new int[]{-1, 3, 5, 1, 4, 2, -9}, 6));
        System.out.println(maxNonOverlapping(new int[]{-2, 6, 6, 3, 5, 4, 1, 2, 8}, 10));
        System.out.println(maxNonOverlapping(new int[]{0, 0, 0}, 0));
    }

    public static int maxNonOverlapping(int[] nums, int target) {

        int N = nums.length;

        int[] preSum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        Map<Integer, Integer> position = new HashMap<>();

        position.put(0, 0);

        int latestAns = -1;

        int ans = 0;

        for (int i = 1; i <= N; i++) {
            int expect = preSum[i] - target;

            if (position.containsKey(expect)) {
                Integer index = position.get(expect);
                if (index >= latestAns) {
                    ans++;
                    latestAns = i;
                }
            }

            position.put(preSum[i], i);
        }

        return ans;
    }

}
