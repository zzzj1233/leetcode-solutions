package com.zzzj.leet;

public class Leet2483 {

    public static int bestClosingTime(String customers) {

        int N = customers.length();

        int[] preSum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + (customers.charAt(i - 1) == 'Y' ? 1 : -1);
        }

        int max = 0;

        for (int i = 0; i <= N; i++) {
            if (preSum[i] > preSum[max]) {
                max = i;
            }
        }

        return max;
    }

}
