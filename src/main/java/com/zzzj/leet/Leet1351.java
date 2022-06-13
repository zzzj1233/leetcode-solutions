package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-07 18:44
 */
public class Leet1351 {

    public static void main(String[] args) {
        System.out.println(countNegatives(LeetUtils.convertInts("[[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]")));
    }

    public static int countNegatives(int[][] grid) {
        // 递减排列

        int n = grid.length;
        int m = grid[0].length;

        int ans = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {

                if (grid[i][j] < 0) {
                    // m = 4, j = 3
                    // i = 0
                    ans += (m - j) * (n - i);
                    m = j;
                    break;
                }

            }

        }

        return ans;
    }

}
