package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-10-28 14:48
 */
public class Leet1921 {

    public static void main(String[] args) {
        System.out.println(eliminateMaximum(new int[]{4, 2, 3}, new int[]{2, 1, 1}));
    }

    public static int eliminateMaximum(int[] dist, int[] speed) {

        int N = dist.length;

        int[] time = new int[N];

        for (int i = 0; i < N; i++) {
            time[i] = dist[i] % speed[i] == 0 ? dist[i] / speed[i] : dist[i] / speed[i] + 1;
        }

        Arrays.sort(time);

        for (int i = 0; i < N; i++) {
            if (time[i] - i <= 0){
                return i;
            }
        }

        return N;
    }

}
