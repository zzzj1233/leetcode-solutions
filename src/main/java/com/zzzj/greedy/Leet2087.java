package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2023-02-28 15:49
 */
public class Leet2087 {

    public static void main(String[] args) {
        System.out.println(minCost(
                new int[]{1, 0},
                new int[]{2, 3},
                new int[]{5, 4, 3},
                new int[]{8, 2, 6, 7}
        ));
    }

    public static int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {

        /*
                 int ans = 0;
            for(int i = min(startPos[0], homePos[0]); i <= max(startPos[0], homePos[0]); ++i)
                ans += rowCosts[i];
            for(int i = min(startPos[1], homePos[1]); i <= max(startPos[1], homePos[1]); ++i)
                ans += colCosts[i];
            return ans - rowCosts[startPos[0]] - colCosts[startPos[1]];
         */
        int curX = startPos[0];
        int curY = startPos[1];

        int tarX = homePos[0];
        int tarY = homePos[1];

        int ans = 0;

        int xStart = Math.min(curX, tarX);
        int xEnd = Math.max(curX, tarX);

        int yStart = Math.min(curY, tarY);
        int yEnd = Math.max(curY, tarY);

        for (int i = xStart; i <= xEnd; i++) {
            ans += rowCosts[i];
        }

        for (int i = yStart; i <= yEnd; i++) {
            ans += colCosts[i];
        }

        return ans - rowCosts[curX] - colCosts[curY];
    }

}
