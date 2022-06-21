package com.zzzj.dp;


/**
 * @author zzzj
 * @create 2021-11-05 14:16
 */
public class Leet1014 {

    public static void main(String[] args) {
        System.out.println(maxScoreSightseeingPair(new int[]{8, 1, 5, 2, 6}));
    }

    public static int maxScoreSightseeingPair(int[] values) {
        int max = values[0];

        int ans = 0;

        for (int i = 1; i < values.length; i++) {
            ans = Math.max(ans, values[i] - i + max);
            max = Math.max(max, values[i] + i);
        }

        return ans;
    }


}
