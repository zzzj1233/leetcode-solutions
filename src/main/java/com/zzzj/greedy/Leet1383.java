package com.zzzj.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-10-24 16:55
 */
public class Leet1383 {

    public static void main(String[] args) {

        System.out.println(maxPerformance(7, new int[]{9, 4, 4, 4, 4, 1, 1}, new int[]{7, 8, 4, 2, 1, 8, 1}, 6));

    }

    public static int maxPerformance(int n, int[] speed, int[] efficiency, int k) {

        int[][] combined = new int[n][2];

        for (int i = 0; i < n; i++) {
            combined[i][0] = speed[i];
            combined[i][1] = efficiency[i];
        }

        Arrays.sort(combined, (o1, o2) -> o2[1] - o1[1]);

        PriorityQueue<Integer> queue = new PriorityQueue<>(k);

        long ans = 0;

        final int MOD = 1000000007;

        long sum = 0;

        for (int i = 0; i < n; i++) {
            int s = combined[i][0];
            int e = combined[i][1];

            if (queue.size() < k) {
                queue.add(s);
                sum += s;
            } else {
                if ((sum - queue.peek() + s) * e > sum) {
                    sum -= queue.remove();
                    sum += s;
                    queue.add(s);
                }
            }
            ans = Math.max(ans, sum * e);
        }

        return (int) (ans % MOD);
    }

}
