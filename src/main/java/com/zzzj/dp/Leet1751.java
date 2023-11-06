package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-10-31 15:08
 */
public class Leet1751 {

    public static void main(String[] args) {

        System.out.println(maxValue(
                LeetUtils.convertInts("[[1,2,4],[3,4,3],[2,3,1]]"),
                2
        ));

        System.out.println(maxValue(
                LeetUtils.convertInts("[[1,2,4],[3,4,3],[2,3,10]]"),
                2
        ));

        System.out.println(maxValue(
                LeetUtils.convertInts("[[1,1,1],[2,2,2],[3,3,3],[4,4,4]]"),
                3
        ));

        System.out.println(maxValue(
                LeetUtils.convertInts("[[19,42,7],[41,73,15],[52,73,84],[84,92,96],[6,64,50],[12,56,27],[22,74,44],[38,85,61]]"),
                5
        ));

    }

    private static final int START = 0;

    private static final int END = 1;

    private static final int VALUE = 2;

    public static int maxValue(int[][] events, int k) {

        Arrays.sort(events, (o1, o2) -> {
            int diff = o1[END] - o2[END];
            return diff == 0 ? o1[START] - o2[START] : diff;
        });

        int N = events.length;

        int[][] dp = new int[N + 1][k];

        for (int i = 1; i <= N; i++) {

            int[] event = events[i - 1];

            int start = event[START];

            int end = event[END];

            int val = event[VALUE];

            dp[i][0] = Math.max(
                    dp[i - 1][0],
                    val
            );

            int limit = Math.min(k - 1, i);

            for (int times = 1; times <= limit; times++) {

                int last = search(events, start);

                if (last == -1)
                    dp[i][times] = dp[i - 1][times];
                else
                    dp[i][times] = Math.max(
                            dp[i - 1][times],
                            dp[last + 1][times - 1] + val
                    );
            }

        }

        return Arrays.stream(dp[N]).max().orElse(0);
    }

    private static int search(int[][] events, int start) {

        int left = 0;

        int right = events.length - 1;

        int res = -1;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (events[mid][END] < start) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return res;
    }
}
