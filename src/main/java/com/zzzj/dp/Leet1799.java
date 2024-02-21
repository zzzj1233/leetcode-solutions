package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2024-01-25 16:44
 */
public class Leet1799 {

    public static void main(String[] args) {

        System.out.println(maxScore(new int[]{1, 2}));

        System.out.println(maxScore(new int[]{3, 4, 6, 8}));

        System.out.println(maxScore(new int[]{1, 2, 3, 4, 5, 6}));

    }

    public static int maxScore(int[] nums) {

        int N = nums.length;

        int limit = 1 << N;

        int M = N / 2;

        int[][] f = new int[M + 1][limit];

        int[][] gcd = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int v = gcd(nums[i], nums[j]);
                gcd[i][j] = v;
                gcd[j][i] = v;
            }
        }

        for (int i = 1; i <= M; i++) {

            for (int stat = 0; stat < limit; stat++) {

                if (Integer.bitCount(stat) != i << 1) {
                    continue;
                }

                for (int bit1 = 0; bit1 < N; bit1++) {

                    if ((stat & (1 << bit1)) == 0)
                        continue;

                    for (int bit2 = bit1 + 1; bit2 < N; bit2++) {

                        if (bit2 == bit1 || (stat & (1 << bit2)) == 0)
                            continue;

                        int ps = stat ^ (1 << bit1) ^ (1 << bit2);

                        f[i][stat] = Math.max(
                                f[i][stat],
                                f[i - 1][ps] + (i * gcd[bit1][bit2])
                        );
                    }

                }

            }

        }

        return f[M][limit - 1];
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
