package com.zzzj.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-09-25 23:18
 */
public class Leet1182 {

    public static void main(String[] args) {
        System.out.println(shortestDistanceColor(new int[]{1, 1, 2, 1, 3, 2, 2, 3, 3}, LeetUtils.convertInts("[[1,3],[2,2],[6,1]]")));
    }

    public static List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int N = colors.length;

        int[][] left = new int[N][4];
        int[][] right = new int[N][4];

        Arrays.fill(left[0], Integer.MAX_VALUE);
        left[0][colors[0]] = 0;

        for (int i = 1; i < N; i++) {
            left[i][colors[i]] = 0;
            for (int j = 1; j <= 3; j++) {
                if (colors[i] == j) {
                    continue;
                }
                left[i][j] = left[i - 1][j] == Integer.MAX_VALUE ? Integer.MAX_VALUE : left[i - 1][j] + 1;
            }
        }

        Arrays.fill(right[N - 1], Integer.MAX_VALUE);
        right[N - 1][colors[N - 1]] = 0;

        for (int i = N - 2; i >= 0; i--) {
            right[i][colors[i]] = 0;
            for (int j = 1; j <= 3; j++) {
                if (colors[i] == j) {
                    continue;
                }
                right[i][j] = right[i + 1][j] == Integer.MAX_VALUE ? Integer.MAX_VALUE : right[i + 1][j] + 1;
            }
        }

        List<Integer> ans = new ArrayList<>(queries.length);

        for (int[] query : queries) {
            int index = query[0];
            int color = query[1];

            int min = Math.min(left[index][color], right[index][color]);

            if (min == Integer.MAX_VALUE) {
                ans.add(-1);
            } else {
                ans.add(min);
            }
        }

        return ans;
    }

}
