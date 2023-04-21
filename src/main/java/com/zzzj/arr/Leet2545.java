package com.zzzj.arr;

import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-04-12 17:36
 */
public class Leet2545 {

    public static int[][] sortTheStudents(int[][] score, int k) {

        int M = score.length;

        PriorityQueue<int[]> queue = new PriorityQueue<>(M, (o1, o2) -> o2[0] - o1[0]);

        for (int i = 0; i < M; i++) {
            queue.add(new int[]{score[i][k], i});
        }

        int[][] ans = new int[M][];

        for (int i = 0; i < M; i++) {
            ans[i] = score[queue.remove()[1]];
        }

        return ans;
    }

}
