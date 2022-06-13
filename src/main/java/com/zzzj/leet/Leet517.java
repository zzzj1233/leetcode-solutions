package com.zzzj.leet;

import com.zzzj.util.Unresolved;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-02 15:19
 */
@Unresolved
public class Leet517 {

    public static void main(String[] args) {
        System.out.println(findMinMoves(new int[]{1, 0, 5}));
    }

    public static int findMinMoves(int[] machines) {
        int N = machines.length;

        int sum = Arrays.stream(machines).sum();

        if (sum % N != 0) {
            return -1;
        }

        int[] preSum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + machines[i - 1];
        }

        int ans = Integer.MAX_VALUE;

        final int per = sum / N;

        for (int i = 0; i < machines.length; i++) {
            int leftTotal = preSum[i];
            int rightTotal = preSum[N] - preSum[i + 1];

            int leftNeed = per * i;
            int rightNeed = (N - i - 1) * per;

            int cur = machines[i];

            if (cur < per) {
                if (leftTotal > leftNeed && rightTotal > rightNeed) {
                    ans = Math.min(ans, Math.max(leftTotal - leftNeed, rightTotal - rightNeed));
                } else if (leftTotal >= leftNeed) { // rightTotal < rightNeed
                    ans = Math.min(ans, Math.max(rightNeed - rightTotal, per - cur));
                } else {
                    ans = Math.min(ans, Math.max(leftNeed - leftTotal, per - cur));
                }
            } else if (cur > per) {
                if (leftTotal < leftNeed && rightTotal < rightNeed) {
                    ans = Math.min(ans, Math.max(leftNeed - leftTotal, rightNeed - rightTotal));
                } else if (leftTotal >= leftNeed) { // rightTotal < rightNeed
                    ans = Math.min(ans, Math.max(leftTotal - leftNeed, cur - per));
                } else {
                    ans = Math.min(ans, Math.max(rightTotal - rightNeed, cur - per));
                }
            } else {
                ans = Math.min(ans, Math.abs(leftTotal - rightTotal));
            }
        }

        return ans;
    }

}
