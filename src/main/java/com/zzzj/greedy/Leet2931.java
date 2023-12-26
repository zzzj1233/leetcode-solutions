package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-11-13 15:01
 */
public class Leet2931 {

    public static void main(String[] args) {

        System.out.println(maxSpending(LeetUtils.convertInts("[[8,5,2],[6,4,1],[9,7,3]]")));

        System.out.println(maxSpending(LeetUtils.convertInts("[[10,8,6,4,2],[9,7,5,3,2]]")));

    }

    public static long maxSpending(int[][] values) {

        int N = values.length;

        int M = values[0].length;

        int[] indexes = new int[N];

        Arrays.fill(indexes, M - 1);

        long ans = 0;

        int end = N * M;

        for (int i = 1; i <= end; i++) {

            int minIndex = -1;

            for (int j = 0; j < N; j++) {
                if (indexes[j] < 0) continue;
                if (minIndex == -1) minIndex = j;
                else if (values[j][indexes[j]] < values[minIndex][indexes[minIndex]])
                    minIndex = j;
            }

            ans += (long) i * values[minIndex][indexes[minIndex]];
            indexes[minIndex]--;
        }

        return ans;
    }

}
