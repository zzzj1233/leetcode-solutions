package com.zzzj.backtracking;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;
import com.zzzj.util.CopyableIterator;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-10-27 11:48
 */
public class Leet1284 {

    public static void main(String[] args) {

        System.out.println(minFlips(LeetUtils.convertInts("[[1, 1], [1, 1]]")));

//        System.exit(0);

        for (int i = 0; i < 10000; i++) {

            int M = 3;

            int[][] mat = LeetUtils.randomMatrix(M, M, 0, 2);

            CopyableIterator<int[][]> it = new CopyableIterator<>(mat, ArrayUtil::copy);

            int r = minFlips(it.next());

            int rr = right(it.next());

            if (r != rr) {
                System.out.println("Error");
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                System.out.println("it.next() = " + Arrays.deepToString(it.next()));
                return;
            }

        }

        System.out.println("Ok");
    }


    public static int minFlips(int[][] mat) {

        int M = mat.length;

        int N = mat[0].length;

        boolean[][] visited = new boolean[M][N];

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                int res = dfs(mat, i, j, visited);

                ans = Math.min(
                        ans,
                        res
                );

            }

        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    private static int dfs(int[][] mat, int x, int y, boolean[][] visited) {

        if (check(mat))
            return 0;

        visited[x][y] = true;

        mat[x][y] ^= 1;

        // 反转
        for (int[] dir : DIRS) {

            int row = dir[0] + x;

            int col = dir[1] + y;

            if (row >= 0 && row < mat.length && col >= 0 && col < mat[row].length) {
                mat[row][col] ^= 1;
            }

        }

        int res = check(mat) ? 1 : Integer.MAX_VALUE;

        for (int i = 0; i < mat.length; i++) {

            for (int j = 0; j < mat[i].length; j++) {

                if (visited[i][j])
                    continue;

                int dfs = dfs(mat, i, j, visited);

                if (dfs != Integer.MAX_VALUE)
                    res = Math.min(res, dfs + 1);
            }

        }

        mat[x][y] ^= 1;

        // 反转
        for (int[] dir : DIRS) {

            int row = dir[0] + x;

            int col = dir[1] + y;

            if (row >= 0 && row < mat.length && col >= 0 && col < mat[row].length) {
                mat[row][col] ^= 1;
            }

        }

        visited[x][y] = false;

        return res;
    }

    private static boolean check(int[][] mat) {

        for (int i = 0; i < mat.length; i++) {

            for (int j = 0; j < mat[i].length; j++) {

                if (mat[i][j] != 0)
                    return false;

            }

        }

        return true;
    }

    public static int right(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        // 存储每个位置需要被反转的次数，包括主动反转以及被动反转
        int[][] cnt = new int[m][n];
        // bit 为一行单元格的位压缩，limit 为 bit 的最大值
        int bit = 0, limit = (1 << n) - 1;
        int ans = 10;
        // 遍历每种状态
        while (bit <= limit) {

            // 初始化中间答案以及反转次数数组
            int tmp = 0;
            for (int i = 0; i < m; i++) {
                Arrays.fill(cnt[i], 0);
            }

            // 计算第一行
            for (int i = 0; i < n; i++) {
                if ((bit & (1 << i)) != 0) {
                    tmp++;
                    cnt[0][i]++;
                    if (m > 1) {
                        cnt[1][i]++;
                    }
                }
                // 考虑左右相邻格子对自身的影响
                if (i - 1 >= 0 && (bit & (1 << (i - 1))) != 0) {
                    cnt[0][i]++;
                }
                if (i + 1 < n && (bit & (1 << (i + 1))) != 0) {
                    cnt[0][i]++;
                }
            }

            // 递推后面的每一行
            for (int i = 1; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 上一个单元格需要再次被反转的情况
                    if (mat[i - 1][j] == 0 && cnt[i - 1][j] % 2 == 1 || mat[i - 1][j] == 1 && cnt[i - 1][j] % 2 == 0) {
                        cnt[i][j]++;
                        if (i + 1 < m) {
                            cnt[i + 1][j]++;
                        }
                        tmp++;
                        if (j - 1 >= 0) {
                            cnt[i][j - 1]++;
                        }
                        if (j + 1 < n) {
                            cnt[i][j + 1]++;
                        }
                    }
                }
            }

            // 检测最后一行是否全为 0
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if (mat[m - 1][i] == 0 && cnt[m - 1][i] % 2 == 1 || mat[m - 1][i] == 1 && cnt[m - 1][i] % 2 == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans = Math.min(ans, tmp);
            }
            bit++;
        }

        if (ans == 10) {
            return -1;
        } else {
            return ans;
        }
    }
}
