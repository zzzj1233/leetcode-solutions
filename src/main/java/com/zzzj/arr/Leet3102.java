package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2024-04-02 15:13
 */
public class Leet3102 {

    public static int minimumDistance(int[][] points) {

        int N = points.length;

        int M = points[0].length;

        final int X = 0;
        final int Y = 0;

        Math.max(
                points[0][X] + points[1][Y] - (points[0][Y] + points[1][X]),
                (points[0][X] - points[1][Y]) - (points[0][Y] + points[1][X])
        );
        
        return -1;
    }

}
