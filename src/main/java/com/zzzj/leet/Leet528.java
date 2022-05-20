package com.zzzj.leet;

import java.util.Random;

/**
 * @author zzzj
 * @create 2022-05-20 17:40
 */
public class Leet528 {

    private static class Solution {

        private final int total;

        private final int[] preSum;

        private final Random RANDOM = new Random();

        public Solution(int[] w) {
            int N = w.length;
            this.preSum = new int[N];
            preSum[0] = w[0];

            for (int i = 1; i < N; i++) {
                preSum[i] = preSum[i - 1] + w[i];
            }

            this.total = preSum[N - 1];
        }

        public int pickIndex() {
            int x = RANDOM.nextInt(total) + 1;
            return binSearch(x);
        }

        public int binSearch(int x) {
            int l = 0;
            int r = preSum.length - 1;

            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (preSum[mid] < x) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return l;
        }

    }

}
