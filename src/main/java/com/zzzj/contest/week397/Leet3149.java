package com.zzzj.contest.week397;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-05-28 17:05
 */
public class Leet3149 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(findPermutation(new int[]{1, 0, 2})));

        System.out.println(Arrays.toString(findPermutation(new int[]{0, 2, 1})));

    }

    public static int[] findPermutation(int[] nums) {

        int N = nums.length;

        int limit = (1 << N);

        int[][] f = new int[limit][N];

        for (int i = 0; i < limit; i++)
            Arrays.fill(f[i], Integer.MAX_VALUE);

        for (int i = 0; i < N; i++)
            f[1 << i][i] = Math.abs(i - nums[0]);

        for (int c = 2; c < N; c++) {

            for (int s = 1; s < limit; s++) {

                int cnt = Integer.bitCount(s);

                if (cnt != c)
                    continue;

                for (int prev = 1; prev < N; prev++) {

                    if ((s & (1 << prev)) == 0)
                        continue;

                    for (int cur = 1; cur < N; cur++) {

                        if (cur == prev)
                            continue;

                        if ((s & (1 << cur)) == 0)
                            continue;

                        int ps = f[s ^ (1 << cur)][prev];

                        if (ps == Integer.MAX_VALUE)
                            continue;

                        f[s][cur] = Math.min(
                                f[s][cur],
                                ps + Math.abs(nums[prev] - cur)
                        );

                    }

                }

            }

        }

        for (int j = 1; j < N; j++) {

            if (f[limit - 2][j] == Integer.MAX_VALUE)
                continue;

            f[limit - 1][0] = Math.min(
                    f[limit - 1][0],
                    f[limit - 2][j] + nums[j]
            );

        }

        int s = limit - 1;

        int[] ans = new int[N];

        int curr = 0;

        int ansIndex = 1;

        while (ansIndex < N) {

            int ns = s ^ (1 << curr);

            for (int next = 0; next < N; next++) {
                if (f[s][curr] - Math.abs(nums[next] - curr) == f[ns][next]) {
                    curr = next;
                    s = ns;
                    ans[ansIndex] = next;
                    ansIndex++;
                    break;
                }
            }

        }

        return ans;
    }


}
