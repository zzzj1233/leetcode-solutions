package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-06-19 11:47
 */
public class Leet1620 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(bestCoordinate(
                LeetUtils.convertInts("[[1,2,5],[2,1,7],[3,1,9]]"),
                2
        )));

        System.out.println(Arrays.toString(bestCoordinate(
                LeetUtils.convertInts("[[1,2,13],[2,1,7],[0,1,9]]"),
                2
        )));

        System.out.println(Arrays.toString(bestCoordinate(
                LeetUtils.convertInts("[[42,0,0]]"),
                2
        )));

        System.out.println(Arrays.toString(bestCoordinate(
                LeetUtils.convertInts("[[30,34,31],[10,44,24],[14,28,23],[50,38,1],[40,13,6],[16,27,9],[2,22,23],[1,6,41],[34,22,40],[40,10,11]]"),
                20
        )));
    }

    public static int[] bestCoordinate(int[][] towers, int radius) {

        int N = towers.length;

        if (N == 1) {
            if (towers[0][2] == 0) return new int[2];
            else return new int[]{towers[0][0], towers[0][1]};
        }

        double maxQ = -1;

        int ansX = -1;

        int ansY = -1;

        for (int i = 0; i <= 50; i++) {

            for (int j = 0; j <= 50; j++) {

                int q = 0;

                for (int k = 0; k < N; k++) {

                    int x = towers[k][0];
                    int y = towers[k][1];

                    int d2 = (x - i) * (x - i) + (y - j) * (y - j);

                    if (d2 <= radius * radius) {
                        q += towers[k][2] / (1 + Math.sqrt(d2));
                    }
                }

                if (q > maxQ) {
                    maxQ = q;
                    ansX = i;
                    ansY = j;
                }
            }

        }

        return new int[]{ansX, ansY};
    }
}
