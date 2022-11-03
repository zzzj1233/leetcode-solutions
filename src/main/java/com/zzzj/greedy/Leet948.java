package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-11-02 11:03
 */
public class Leet948 {

    public static int bagOfTokensScore(int[] tokens, int power) {

        int N = tokens.length;

        Arrays.sort(tokens);

        int cw = power;

        int score = 0;

        int left = 0;

        int right = N - 1;

        while (left <= right) {
            int token = tokens[left];

            if (cw < token) {
                if (score == 0 || left == right) {
                    break;
                }
                cw += tokens[right];
                right--;
                score--;
            }

            cw -= token;
            score++;
            left++;
        }

        return score;
    }

}
