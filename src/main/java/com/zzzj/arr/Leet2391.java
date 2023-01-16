package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2023-01-13 14:22
 */
public class Leet2391 {

    private static final int METAL = 0;

    private static final int PAPER = 1;

    private static final int GLASS = 2;

    public static int garbageCollection(String[] garbage, int[] travel) {

        int N = garbage.length;

        int[][] count = new int[N][3];

        for (int i = 0; i < N; i++) {
            String str = garbage[i];

            int M = str.length();

            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == 'M') {
                    count[i][METAL]++;
                } else if (str.charAt(j) == 'P') {
                    count[i][PAPER]++;
                } else {
                    count[i][GLASS]++;
                }
            }

        }

        int M = travel.length;

        int[] preSum = new int[M + 1];

        for (int i = 1; i <= M; i++) {
            preSum[i] = preSum[i - 1] + travel[i - 1];
        }

        int ans = 0;

        ans += time(count, preSum, METAL);

        ans += time(count, preSum, PAPER);

        ans += time(count, preSum, GLASS);

        return ans;
    }

    public static int time(int[][] count, int[] preSum, int target) {
        int N = count.length;

        int clearTime = 0;

        int moveTime = 0;

        for (int i = 0; i < N; i++) {
            int garbageSize = count[i][target];
            clearTime += garbageSize;
            if (garbageSize > 0) {
                moveTime = preSum[i];
            }
        }

        return clearTime + moveTime;
    }
}
