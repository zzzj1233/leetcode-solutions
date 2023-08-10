package com.zzzj.contest.zjfuture;


/**
 * @author zzzj
 * @create 2023-08-09 16:49
 */
public class Question2 {

    // [1,1,0,1,0,1,0,0,1,0,1]
    // [1,1,1,1,1,1,0,0,0,0,0]，
    public static void main(String[] args) {

        System.out.println(minSwaps(new int[]{1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1}));
        System.out.println(minSwaps(new int[]{1, 0, 1, 0, 1, 0}));

        System.out.println(minSwaps(new int[]{0, 0, 0, 1, 0}));
//
        System.out.println(minSwaps(new int[]{1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1}));

    }

    // [1,0,1,0,1,0,0,1,1,0,1]
    public static int minSwaps(int[] chess) {

        int N = chess.length;

        int[] left = new int[N];

        int cnt = chess[0];

        left[0] = chess[0];

        for (int i = 1; i < N; i++) {

            left[i] = left[i - 1];

            if (chess[i] == 1) {
                left[i]++;
                cnt++;
            }

        }

        int ans = Integer.MAX_VALUE;

        for (int i = cnt - 1; i < N; i++) {
            ans = Math.min(ans, cnt - ( (i + 1) - left[i]));
        }

        return ans;
    }

}
