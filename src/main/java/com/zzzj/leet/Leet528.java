package com.zzzj.leet;

import java.util.Random;

/**
 * @author zzzj
 * @create 2022-05-20 17:40
 */
public class Leet528 {

    private static class Solution {

        private final int total;
        int[] preSum;

        Random random = new Random();

        public Solution(int[] w) {
            int N = w.length;

            preSum = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                preSum[i] = preSum[i - 1] + w[i - 1];
            }

            total = preSum[N];
        }

        public int pickIndex() {
            int randomNum = random.nextInt(total) + 1;

            int low = 0;
            int high = preSum.length - 1;

            int last = -1;

            while (low < high) {
                int mid = low + ((high - low) >> 1);
                if (preSum[mid] < randomNum) {
                    last = low;
                    low = mid + 1;
                } else if (preSum[mid] > randomNum) {
                    high = mid - 1;
                } else {
                    return mid;
                }
            }

            return last;
        }

    }

}
