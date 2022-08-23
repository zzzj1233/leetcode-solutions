package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-08-22 17:25
 */
public class Leet1395 {


    public static int numTeams(int[] rating) {

        int N = rating.length;

        int ans = 0;

        for (int i = 1; i < N - 1; i++) {

            int num = rating[i];

            int leftLt = 0;
            int leftGt = 0;

            for (int j = 0; j < i; j++) {
                if (rating[j] < num) {
                    leftLt++;
                } else {
                    leftGt++;
                }
            }

            int rightLt = 0;
            int rightGt = 0;

            for (int j = i + 1; j < N; j++) {
                if (rating[j] < num) {
                    rightLt++;
                } else {
                    rightGt++;
                }
            }

            ans += (leftGt * rightLt) + (leftLt * rightGt);
        }


        return ans;
    }

}
