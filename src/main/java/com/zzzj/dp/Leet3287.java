package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2024-09-28 21:53
 */
public class Leet3287 {

    public static void main(String[] args) {

        System.out.println(maxValue(
                new int[]{4, 2, 5, 6, 7},
                2
        ));

    }

    public static int maxValue(int[] nums, int k) {

        int N = nums.length;

        // f : boolean[]
        // f[x][y][xor] = f[x - 1][y - 1][z | v]

        boolean[][][] pre = new boolean[N + 1][k + 1][128];

        boolean[][][] suf = new boolean[N + 1][k + 1][128];

        pre[0][0][0] = true;

        suf[N][0][0] = true;

        // N - i

        for (int i = N - 1; i >= 0; i--) {

            int end = Math.min(k + 1, N - i + 1);

            suf[i][0][0] = true;

            for (int j = 1; j < end; j++) {

                for (int v = 0; v < 128; v++) {

                    suf[i][j][v] |= suf[i + 1][j][v];
                    suf[i][j][v | nums[i]] |= suf[i + 1][j - 1][v];

                }

            }
        }

        // System.out.println(Arrays.deepToString(suf[N - 1]));

        for (int i = 1; i <= N; i++) {

            int end = Math.min(k + 1, i + 1);

            pre[i][0][0] = true;

            for (int j = 1; j < end; j++) {

                for (int v = 0; v < 128; v++) {

                    pre[i][j][v] |= pre[i - 1][j][v];
                    pre[i][j][v | nums[i - 1]] |= pre[i - 1][j - 1][v];

                }

            }

        }

        // 0,1,2,3,4,5
        // N = 6
        // k = 2
        // start = k   (左边不包含start)
        // end = N - k (左边不包含end)

        int ans = 0;

        for (int i = k; i <= N - k; i++) {

            for (int v = 0; v < 128; v++) {

                if (pre[i][k][v]) {

                    for (int v2 = 0; v2 < 128; v2++) {
                        if (suf[i][k][v2])
                            ans = Math.max(ans, v ^ v2);
                    }

                }

            }


        }

        return ans;
    }


}
