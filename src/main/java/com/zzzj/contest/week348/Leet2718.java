package com.zzzj.contest.week348;

import com.zzzj.leet.LeetUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-07-27 17:40
 */
public class Leet2718 {

    public static void main(String[] args) {

        System.out.println(matrixSumQueries(3, LeetUtils.convertInts("[[0,0,1],[1,2,2],[0,2,3],[1,0,4]]")));

    }

    public static long matrixSumQueries(int n, int[][] queries) {

        final int TYPE_IDX = 0;
        final int INDEX_IDX = 1;
        final int VALUE_IDX = 2;

        Map<Integer, Integer> row = new HashMap<>();

        Map<Integer, Integer> col = new HashMap<>();

        int N = queries.length;

        long ans = 0;

        for (int i = N - 1; i >= 0; i--) {

            int[] query = queries[i];

            boolean isRow = query[TYPE_IDX] == 0;

            int index = query[INDEX_IDX];

            int value = query[VALUE_IDX];

            if (isRow) {
                Integer old = row.put(index, value);
                if (old == null)
                    ans += (long) (n - col.size()) * value;
            } else {
                Integer old = col.put(index, value);
                if (old == null)
                    ans += (long) (n - row.size()) * value;
            }

        }

        return ans;
    }

}
