package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zzzj
 * @create 2023-10-23 10:22
 */
public class Leet2050 {

    public static void main(String[] args) {

        // 21
        System.out.println(minimumTime(8, LeetUtils.convertInts("[[2,7],[2,6],[3,6],[4,6],[7,6],[7,1],[3,8],[5,8]]"), new int[]{9, 5, 9, 5, 8, 7, 7, 8}));

        System.exit(0);

        System.out.println(minimumTime(3, LeetUtils.convertInts("[[1,3],[2,3]]"), new int[]{3, 2, 5}));

        System.out.println(minimumTime(5, LeetUtils.convertInts("[[1,5],[2,5],[3,5],[3,4],[4,5]]"), new int[]{1, 2, 3, 4, 5}));

    }

    public static int minimumTime(int n, int[][] relations, int[] time) {

        int[] inDegree = new int[n + 1];

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] relation : relations) {
            graph.computeIfAbsent(relation[0], integer -> new HashSet<>()).add(relation[1]);
            inDegree[relation[1]]++;
        }

        LinkedList<Integer> queue = IntStream.range(0, inDegree.length)
                .skip(1)
                .filter(index -> inDegree[index] == 0)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        int ans = 0;

        int[] max = new int[n + 1];

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                Integer node = queue.removeFirst();

                int curCost = time[node - 1];

                ans = Math.max(ans, curCost);

                Set<Integer> adj = graph.getOrDefault(node, Collections.emptySet());

                for (Integer next : adj) {

                    inDegree[next]--;

                    max[next] = Math.max(max[next], curCost);

                    if (inDegree[next] == 0) {
                        time[next - 1] += max[next];
                        queue.addLast(next);
                    }

                }

            }

        }

        return ans;
    }

}
