package com.zzzj.contest.dweek131;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2024-05-27 11:02
 */
public class Leet3160 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(queryResults(4, LeetUtils.convertInts("[[1,4],[2,5],[1,3],[3,4]]"))));

    }

    public static int[] queryResults(int limit, int[][] queries) {

        int N = queries.length;

        Map<Integer, Integer> rec = new HashMap<>();

        Map<Integer, Integer> col = new HashMap<>();

        int[] ans = new int[N];

        for (int i = 0; i < N; i++) {
            int[] query = queries[i];

            int node = query[0];

            int color = query[1];

            Integer old = rec.put(node, color);

            col.put(color, col.getOrDefault(color, 0) + 1);

            if (old != null) {
                Integer oldCnt = col.get(old);
                if (oldCnt == 1)
                    col.remove(old);
                else
                    col.put(old, oldCnt - 1);
            }

            ans[i] = col.size();
        }

        return ans;
    }

}
