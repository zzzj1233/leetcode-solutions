package com.zzzj.contest.zjfuture;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-08-09 16:08
 */
public class Question3 {

    public static void main(String[] args) {

        System.out.println(buildTransferStation(LeetUtils.convertInts("[[0,1,0,0,0],[0,0,0,0,1],[0,0,1,0,0]]")));

        System.out.println(buildTransferStation(LeetUtils.convertInts("[[1,1],[1,1]]")));

    }

    public static int buildTransferStation(int[][] area) {

        int M = area.length;

        int N = area[0].length;

        List<int[]> pos = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (area[i][j] == 1)
                    pos.add(new int[]{i, j});
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                int dis = 0;

                for (int[] p : pos) {
                    dis += Math.abs(p[0] - i) + Math.abs(p[1] - j);
                }

                ans = Math.min(ans, dis);
            }

        }

        return ans;
    }


}
