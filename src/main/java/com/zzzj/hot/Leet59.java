package com.zzzj.hot;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-04-25 15:12
 */
public class Leet59 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(generateMatrix(10)));
    }

    public static int[][] generateMatrix(int n) {
        // 生成一个N平方的矩阵
        // 有N行N列
        int[][] ans = new int[n][n];

        int tr = 0;
        int tc = 0;
        int br = n - 1;
        int bc = br;

        int num = 1;

        while (tr <= br && tc <= bc) {
            int row = br - tr;
            int col = bc - tc;

            // 第一行
            for (int i = tc; i <= bc; i++) {
                ans[tr][i] = num++;
            }

            // 最后一列
            for (int i = tr + 1; i <= br; i++) {
                ans[i][bc] = num++;
            }

            // 最后一行
            for (int i = bc - 1; i >= tc; i--) {
                ans[br][i] = num++;
            }

            // 第一列
            for (int i = br - 1; i > tr; i--) {
                ans[i][tr] = num++;
            }

            tr++;
            tc++;
            br--;
            bc--;
        }

        return ans;
    }

}
