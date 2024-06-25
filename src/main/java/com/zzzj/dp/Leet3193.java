package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2024-06-24 17:37
 */
public class Leet3193 {

    public static void main(String[] args) {

        System.out.println(numberOfPermutations(3, LeetUtils.convertInts("[[1,0],[2,0]]")));

        System.out.println(numberOfPermutations(3, LeetUtils.convertInts("[[2,2],[1,1],[0,0]]")));

        System.out.println(numberOfPermutations(3, LeetUtils.convertInts("[[2,2],[0,0]]")));

    }

    public static int numberOfPermutations(int n, int[][] requirements) {

        Map<Integer, Integer> req = new HashMap<>(n);

        for (int i = 0; i <= n; i++)
            req.put(i, -1);

        for (int[] requirement : requirements)
            req.put(requirement[0], requirement[1]);

        long[][] f = new long[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(f[i], -1);
        }

        f[0][0] = 1;

        final int MOD = 1000000007;

        for (int i = 1; i < n; i++) {

            Integer r = req.get(i);

            // 至多生成i个逆序对

            // 有要求
            if (r != -1) {

                f[i][r] = 0;

                for (int k = r; k >= Math.max(0, r - i); k--) {
                    if (f[i - 1][k] != -1)
                        f[i][r] = (f[i][r] + f[i - 1][k]) % MOD;
                }

            } else { // 无要求

                for (int j = 0; j < n; j++) {

                    f[i][j] = 0;

                    for (int k = j; k >= Math.max(0, j - i); k--) {
                        if (f[i - 1][k] != -1)
                            f[i][j] = (f[i][j] + f[i - 1][k]) % MOD;
                    }

                }

            }

        }

        if (req.get(n - 1) != -1)
            return (int) f[n - 1][req.get(n - 1)];

        return (int) f[n - 1][n - 1];
    }

}
