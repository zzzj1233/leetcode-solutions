package com.zzzj.contest.dweek123;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2024-02-04 16:24
 */
public class Q2 {

    public static void main(String[] args) {

        System.out.println(numberOfPairs(LeetUtils.convertInts("[[0,4],[6,2],[0,3]]")));

        System.out.println(numberOfPairs(LeetUtils.convertInts("[[6,2],[4,4],[2,6]]")));

        System.out.println(numberOfPairs(LeetUtils.convertInts("[[3,1],[1,3],[1,1]]")));

    }

    public static int numberOfPairs(int[][] points) {

        int N = points.length;

        int ans = 0;

        for (int i = 0; i < N; i++) {

            int[] p1 = points[i];

            OUTER:
            for (int j = i + 1; j < N; j++) {

                int[] p2 = points[j];

                if (!check(p1, p2) && !check(p2, p1))
                    continue;

                int colMin = Math.min(p1[0], p2[0]);

                int colMax = Math.max(p1[0], p2[0]);

                int rowMin = Math.min(p1[1], p2[1]);

                int rowMax = Math.max(p1[1], p2[1]);

                // 检查中间是否有元素
                for (int k = 0; k < N; k++) {

                    if (k == i || k == j)
                        continue;

                    int[] p3 = points[k];

                    int col = p3[0];

                    int row = p3[1];

                    if (col >= colMin && col <= colMax && row >= rowMin && row <= rowMax) {
                        continue OUTER;
                    }

                }

                ans++;
            }

        }

        return ans;
    }

    public static boolean check(int[] p1, int[] p2) {
        return p1[0] <= p2[0] && p1[1] >= p2[1];
    }

}
