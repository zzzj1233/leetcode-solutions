package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-11-08 16:21
 */
public class Leet2392 {

    public static void main(String[] args) {

        System.out.println(Arrays.deepToString(buildMatrix(
                8,
                LeetUtils.convertInts("[[1,2],[7,3],[4,3],[5,8],[7,8],[8,2],[5,8],[3,2],[1,3],[7,6],[4,3],[7,4],[4,8],[7,3],[7,5]]"),
                LeetUtils.convertInts("[[5,7],[2,7],[4,3],[6,7],[4,3],[2,3],[6,2]]")
        )));

    }

    public static int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {

        int[] rowInDegree = new int[k + 1];

        int[] colInDegree = new int[k + 1];

        Map<Integer, Set<Integer>> rowGraph = new HashMap<>();

        Map<Integer, Set<Integer>> colGraph = new HashMap<>();

        for (int[] condition : rowConditions) {
            if (!rowGraph.getOrDefault(condition[0], Collections.emptySet()).contains(condition[1])) {
                rowInDegree[condition[1]]++;
                rowGraph.computeIfAbsent(condition[0], integer -> new HashSet<>()).add(condition[1]);
            }
        }

        for (int[] condition : colConditions) {
            if (!colGraph.getOrDefault(condition[0], Collections.emptySet()).contains(condition[1])) {
                colInDegree[condition[1]]++;
                colGraph.computeIfAbsent(condition[0], integer -> new HashSet<>()).add(condition[1]);
            }
        }

        boolean[] visited = new boolean[k + 1];

        int[] row = new int[k];

        final int[][] EMPTY = new int[0][0];

        for (int i = 0; i < k; i++) {

            for (int node = 1; node <= k; node++) {
                if (!visited[node] && rowInDegree[node] <= 0) {
                    row[i] = node;
                    break;
                }
            }

            if (row[i] == 0)
                return EMPTY;

            visited[row[i]] = true;

            for (Integer adj : rowGraph.getOrDefault(row[i], Collections.emptySet())) {
                rowInDegree[adj]--;
            }

        }

        Arrays.fill(visited, false);

        Map<Integer, Integer> col = new HashMap<>();

        for (int i = 0; i < k; i++) {

            int find = -1;

            for (int node = 1; node <= k; node++) {
                if (!visited[node] && colInDegree[node] <= 0) {
                    col.put(node, i);
                    find = node;
                    break;
                }
            }

            if (find == -1)
                return EMPTY;

            visited[find] = true;

            for (Integer adj : colGraph.getOrDefault(find, Collections.emptySet())) {
                colInDegree[adj]--;
            }

        }

        int[][] ans = new int[k][k];

        for (int i = 0; i < k; i++)
            ans[i][col.get(row[i])] = row[i];

        return ans;
    }

}
