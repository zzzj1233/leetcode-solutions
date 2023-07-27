package com.zzzj.contest.dweek107;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-07-25 16:11
 */
public class Leet2747 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(countServers(3, LeetUtils.convertInts("[[1,3],[2,6],[1,5]]"), 5, new int[]{10, 11})));

    }

    public static int[] countServers(int n, int[][] logs, int x, int[] queries) {

        int N = queries.length;

        int[][] combined = new int[N][2];

        for (int i = 0; i < N; i++) {
            combined[i][0] = queries[i];
            combined[i][1] = i;
        }

        Arrays.sort(combined, Comparator.comparingInt(o -> o[0]));

        TreeMap<Integer, Set<Integer>> treeMap = new TreeMap<>();

        for (int i = 0; i < logs.length; i++) {
            treeMap.computeIfAbsent(logs[i][1], integer -> new HashSet<>())
                    .add(logs[i][0]);
        }

        int[] ans = new int[N];

        Arrays.fill(ans, n);

        Integer receiveTime = treeMap.firstKey();

        int queryIndex = 0;

        int workingCnt = 0;

        TreeMap<Integer, Set<Integer>> endMap = new TreeMap<>();

        while (receiveTime != null && queryIndex < N) {

            while (endMap.floorKey(receiveTime) != null) {

                Map.Entry<Integer, Set<Integer>> floor = endMap.floorEntry(receiveTime);

                Set<Integer> indexes = floor.getValue();

                for (Integer ansIndex : indexes) {
                    ans[ansIndex] -= workingCnt;
                }

                workingCnt -= indexes.size();

                treeMap.remove(floor.getKey());
            }

            while (queryIndex < N && receiveTime >= combined[queryIndex][0] - x) {

                workingCnt += treeMap.get(receiveTime).size();

                endMap.computeIfAbsent(combined[queryIndex][0], integer -> new TreeSet<>())
                        .add(combined[queryIndex][1]);

                queryIndex++;
            }

            receiveTime = treeMap.ceilingKey(receiveTime + 1);
        }

        return ans;
    }

}
