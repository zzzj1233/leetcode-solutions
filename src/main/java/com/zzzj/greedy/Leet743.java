package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import javax.swing.event.AncestorEvent;
import java.util.Arrays;

public class Leet743 {

    public static void main(String[] args) {

        System.out.println(networkDelayTime(LeetUtils.convertInts("[[1,2,1]]"), 2, 2));

    }

    public static int networkDelayTime(int[][] times, int n, int k) {

        int[][] dis = new int[n][n];

        for (int i = 0; i < n; i++)
            Arrays.fill(dis[i], Integer.MAX_VALUE);

        for (int i = 0; i < n; i++)
            dis[i][i] = 0;

        for (int[] time : times)
            dis[time[0] - 1][time[1] - 1] = time[2];

        for (int t = 0; t < n; t++) {

            for (int v = 0; v < n; v++) {

                for (int w = 0; w < n; w++) {

                    if (dis[v][t] != Integer.MAX_VALUE && dis[t][w] != Integer.MAX_VALUE && dis[v][t] + dis[t][w] < dis[v][w])
                        dis[v][w] = dis[v][t] + dis[t][w];
                }

            }

        }

        int max = -1;

        for (int i = 0; i < n; i++) {
            if (i != k - 1)
                max = Math.max(max, dis[k - 1][i]);
        }

        return max == Integer.MAX_VALUE ? -1 : max;
    }

}
