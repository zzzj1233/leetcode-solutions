package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-28 11:02
 */
public class Leet135 {

    // 每个孩子最少一个糖果
    // 相邻两个孩子评分更高的孩子会获得更多的糖果

    public static void main(String[] args) {
        System.out.println(candy(new int[]{1, 0, 2}));
        System.out.println(candy(new int[]{1, 2, 2}));
        System.out.println(candy(new int[]{1, 4, 2, 0}));
    }

    public static int candy(int[] ratings) {
        int N = ratings.length;

        if (N == 0) {
            return 0;
        }

        int[] score = new int[N];

        score[0] = 1;

        for (int i = 1; i < N; i++) {
            if (ratings[i] > ratings[i - 1]) {
                score[i] = score[i - 1] + 1;
            } else {
                score[i] = 1;
            }
        }

        for (int i = N - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                score[i] = Math.max(score[i + 1] + 1, score[i]);
            }
        }

        return Arrays.stream(score).sum();
    }


}
