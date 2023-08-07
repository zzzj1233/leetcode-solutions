package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-06-09 17:03
 */
public class Leet2712 {

    public static void main(String[] args) {

        System.out.println(minimumCost("0"));

        System.out.println(minimumCost("010101"));

    }

    public static long minimumCost(String s) {

        int N = s.length();

        if (N == 1) return 0;

        final int ZERO = 0;

        final int ONE = 1;

        long[][] left = new long[N + 1][2];

        left[0][ZERO] = s.charAt(0) == '0' ? 0 : 1;
        left[0][ONE] = s.charAt(0) == '1' ? 0 : 1;

        int continuousZero = s.charAt(0) == '0' ? 1 : 0;
        int continuousOne = s.charAt(0) == '1' ? 1 : 0;

        for (int i = 1; i < N; i++) {

            char c = s.charAt(i);

            if (c == '0') {
                continuousOne = 0;
                continuousZero++;
                // 全部变成0
                left[i][ZERO] = left[i - 1][ZERO];
                left[i][ONE] = (i - continuousZero >= 0 ? left[i - continuousZero][ONE] : 0) + (i + 1 + (i - continuousZero + 1));
            } else {
                continuousZero = 0;
                continuousOne++;
                left[i][ONE] = left[i - 1][ONE];
                left[i][ZERO] = (i - continuousOne >= 0 ? left[i - continuousOne][ZERO] : 0) + (i + 1 + (i - continuousOne + 1));
            }

        }

        long[][] right = new long[N + 1][2];

        right[N - 1][ZERO] = s.charAt(N - 1) == '0' ? 0 : 1;
        right[N - 1][ONE] = s.charAt(N - 1) == '1' ? 0 : 1;

        continuousZero = s.charAt(N - 1) == '0' ? 1 : 0;
        continuousOne = s.charAt(N - 1) == '1' ? 1 : 0;

        for (int i = N - 2; i >= 0; i--) {
            char c = s.charAt(i);

            if (c == '0') {
                continuousOne = 0;
                continuousZero++;
                // 全部变成0
                right[i][ZERO] = right[i + 1][ZERO];
                right[i][ONE] = right[i + continuousZero][ONE] + ((N - i) + (N - i - continuousZero));
            } else {
                continuousZero = 0;
                continuousOne++;
                right[i][ONE] = right[i + 1][ONE];
                right[i][ZERO] = right[i + continuousOne][ZERO] + ((N - i) + (N - i - continuousOne));
            }
        }

        long ans = Long.MAX_VALUE;

        for (int i = 0; i < N - 1; i++) {
            ans = Math.min(
                    ans,
                    Math.min(
                            left[i][ZERO] + right[i + 1][ZERO],
                            left[i][ONE] + right[i + 1][ONE]
                    )
            );
        }

        return ans;
    }


}
