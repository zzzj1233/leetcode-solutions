package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-10-20 14:53
 */
public class Leet1889 {

    public static void main(String[] args) {


    }

    public static int minWastedSpace(int[] packages, int[][] boxes) {

        int N = packages.length;

        Arrays.sort(packages);

        long[] preSum = new long[N + 1];

        for (int i = 1; i <= N; i++)
            preSum[i] = preSum[i - 1] + packages[i - 1];

        long ans = Long.MAX_VALUE;

        final int MOD = 1000000007;

        OUTER:
        for (int[] box : boxes) {

            long redundancy = 0;

            int prevIndex = -1;

            Arrays.sort(box);

            for (int b : box) {

                int left = 0;
                int right = N - 1;
                int index = -1;

                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (packages[mid] <= b) {
                        index = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }

                if (index == -1)
                    continue;

                int cnt = index - prevIndex;

                long sum = (long) b * cnt;

                long ps = (preSum[index + 1] - preSum[prevIndex + 1]);

                redundancy += (sum - ps);

                prevIndex = index;
            }

            if (prevIndex == N - 1)
                ans = Math.min(ans, redundancy);
        }

        return ans == Long.MAX_VALUE ? -1 : (int) (ans % MOD);
    }


}
