package com.zzzj.contest.week141;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2024-10-13 14:07
 */
public class Leet3316 {

    public static void main(String[] args) {

        System.out.println(

                maxRemovals(
                        "ekyeyeyy",
                        "eyeyy",
                        new int[]{0, 4}
                )

        );

    }

    public static int maxRemovals(String source, String pattern, int[] targetIndices) {

        int N = source.length();

        int M = pattern.length();

        int[][] f = new int[N + 1][M + 1];

        for (int i = 0; i <= N; i++) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
            f[i][0] = 0;
        }

        Set<Integer> indexes = Arrays.stream(targetIndices).boxed().collect(Collectors.toSet());

        for (int i = 1; i <= N; i++) {

            for (int j = 1; j <= M; j++) {

                if (source.charAt(i - 1) == pattern.charAt(j - 1)) {

                    if (indexes.contains(i - 1)) {

                        if (f[i - 1][j] != Integer.MAX_VALUE) {
                            f[i][j] = f[i - 1][j];
                        }

                        if (f[i - 1][j - 1] != Integer.MAX_VALUE) {
                            f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + 1);
                        }

                    } else {
                        f[i][j] = Math.min(
                                f[i - 1][j],
                                f[i - 1][j - 1]
                        );
                    }

                } else {
                    f[i][j] = f[i - 1][j];
                }

            }

        }

        return targetIndices.length - f[N][M];
    }


}
