package com.zzzj.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-12-13 16:15
 */
public class Lint1833 {

    public static void main(String[] args) {

        System.out.println(minimumBoxes(new int[]{1, 2, 2, 1, 1, 1}, 3));

    }

    public static int minimumBoxes(int[] boxes, int target) {

        int N = boxes.length;

        int[] dp = new int[N + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        Map<Long, Integer> index = new HashMap<>(N);

        long sum = 0;

        index.put(0L, 0);

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i <= boxes.length; i++) {

            sum += boxes[i - 1];

            Integer prev = index.get(sum - target);

            if (prev != null) {
                if (prev > 0 && dp[prev] != Integer.MAX_VALUE)
                    ans = Math.min(ans, dp[prev] + (i - prev));
                else
                    dp[i] = i - prev;
            }

            dp[i] = Math.min(
                    dp[i],
                    dp[i - 1]
            );

            index.put(sum, i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
