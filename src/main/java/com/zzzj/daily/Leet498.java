package com.zzzj.daily;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-14 11:15
 */
public class Leet498 {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(findDiagonalOrder(LeetUtils.convertInts("[[1,2,3],[4,5,6],[7,8,9]]"))));
//        System.out.println(Arrays.toString(findDiagonalOrder(LeetUtils.convertInts("[[1,2],[3,4]]"))));

        for (int i = 0; i < 1000; i++) {
            int N = LeetUtils.random.nextInt(100) + 1;
            int M = LeetUtils.random.nextInt(100) + 1;
            int[][] matrix = LeetUtils.randomMatrix(N, M, 1, 100);
            if (!Arrays.equals(findDiagonalOrder(matrix), right(matrix))) {
                System.out.println("Error");
                return;
            }
        }
    }

    public static int[] findDiagonalOrder(int[][] mat) {
        int N = mat.length;
        int M = mat[0].length;

        int[] ans = new int[N * M];
        int index = 1;
        ans[0] = mat[0][0];

        if (N == 1 && M == 1) {
            return ans;
        }
        // bottom -> top : i++,如果i == N - 1,那么j++
        // top -> bottom : j++,如果j == M - 1,那么i++

        // i = 行, j = 列
        int i = 0;
        int j = 0;

        boolean topToBottom = true;

        while (true) {
            if (topToBottom) {
                if (j + 1 == M) {
                    i++;
                } else {
                    j++;
                }
                // 直到j<=0 && i < N
                while (true) {
                    ans[index] = mat[i][j];
                    index++;
                    if (j - 1 < 0 || i + 1 == N) {
                        break;
                    }
                    j--;
                    i++;
                }
            } else {
                if (i + 1 == N) {
                    j++;
                } else {
                    i++;
                }
                // 直到i>=0 && j<M
                while (true) {
                    ans[index] = mat[i][j];
                    index++;
                    if (i - 1 < 0 || j + 1 == M) {
                        break;
                    }
                    i--;
                    j++;
                }
            }
            if (i == N - 1 && j == M - 1) {
                break;
            }
            topToBottom = !topToBottom;
        }

        return ans;
    }

    public static int[] right(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] res = new int[m * n];
        int pos = 0;
        for (int i = 0; i < m + n - 1; i++) {
            if (i % 2 == 1) {
                int x = i < n ? 0 : i - n + 1;
                int y = i < n ? i : n - 1;
                while (x < m && y >= 0) {
                    res[pos] = mat[x][y];
                    pos++;
                    x++;
                    y--;
                }
            } else {
                int x = i < m ? i : m - 1;
                int y = i < m ? 0 : i - m + 1;
                while (x >= 0 && y < n) {
                    res[pos] = mat[x][y];
                    pos++;
                    x--;
                    y++;
                }
            }
        }
        return res;
    }

}
