package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2024-06-24 16:12
 */
public class Leet629 {

    public static void main(String[] args) {

        System.out.println(kInversePairs(3, 1));

        System.out.println(kInversePairs(3, 0));

    }

    public static int kInversePairs(int n, int k) {

        final int MOD = 1000000007;

        long[][] f = new long[n + 1][k + 1];

        f[0][0] = 1;

        for (int i = 1; i <= n; i++) {

            // 至多生成i - 1个排列
            for (int j = 0; j <= k; j++) {

                f[i][j] = ((j - 1 >= 0 ? f[i][j - 1] : 0) + f[i - 1][j] - (j - i >= 0 ? f[i - 1][j - i] : 0));

                if (f[i][j] < 0)
                    f[i][j] += MOD;

                f[i][j] %= MOD;
            }

        }

        // System.out.println("f = " + Arrays.deepToString(f));

        return (int) f[n][k];
    }

}
