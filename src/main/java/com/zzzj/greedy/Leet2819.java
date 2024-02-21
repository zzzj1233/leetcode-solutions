package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-01-23 17:27
 */
public class Leet2819 {

    public long[] minimumRelativeLosses(int[] prices, int[][] queries) {

        // p < k :  p
        // p > k :  k - ( p - k )
        // any case : k - abc( p - k )

        Arrays.sort(prices);

        int N = prices.length;

        long[] ps = new long[N + 1];

        for (int i = 1; i <= N; i++)
            ps[i] = ps[i - 1] + prices[i];

        int M = queries.length;

        long[] ans = new long[M];

        for (int i = 0; i < M; i++) {

        }

        return ans;
    }

    public static long query(
            long[] ps,
            int[] prices,
            int[] query
    ) {

        int N = prices.length;

        int K = query[0];

        int M = query[1];

        // 找到分界点

        int left = 0;

        int right = N - 1;

        int sep = -1;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (prices[mid] >= K) {
                sep = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }

        if (sep == -1)
            return ps[M];

        int ll = Math.min(sep - 1, M - 1);

        int lc = ll + 1;

        int rl = N - (M - lc);

        // long res = ps[ll + 1] +;

        return -1;
    }

}
