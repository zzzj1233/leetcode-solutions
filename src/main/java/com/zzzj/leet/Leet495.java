package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-25 12:14
 */
public class Leet495 {

    // 输入：timeSeries = [1,2], duration = 2
    // 输出：3

    // 输入：timeSeries = [1,4], duration = 2
    // 输出：4

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int ans = duration;

        for (int i = 1; i < timeSeries.length; i++) {
            // 4 - 1 = 3
            // 2 - 1 = 1
            int interval = timeSeries[i] - timeSeries[i - 1];

            if (interval < duration) {
                ans += interval;
            } else {
                ans += duration;
            }
        }

        return ans;
    }

}
