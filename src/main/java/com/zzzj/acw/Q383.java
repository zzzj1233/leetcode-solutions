package com.zzzj.acw;

import java.util.*;

/**
 * @author zzzj
 * @create 2024-02-05 16:58
 */
public class Q383 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner("2\n" +
                "5 8\n" +
                "1 2 3\n" +
                "1 3 2\n" +
                "1 4 5\n" +
                "2 3 1\n" +
                "2 5 3\n" +
                "3 4 2\n" +
                "3 5 4\n" +
                "4 5 3\n" +
                "1 5\n" +
                "5 6\n" +
                "2 3 1\n" +
                "3 2 1\n" +
                "3 1 10\n" +
                "4 5 2\n" +
                "5 2 7\n" +
                "5 2 7\n" +
                "4 1");

        int T = scanner.nextInt();

        for (int caseX = 0; caseX < T; caseX++) {

            int N = scanner.nextInt();

            int E = scanner.nextInt();

            Map<Integer, Map<Integer, Integer>> graph = new HashMap<>(N);

            for (int i = 0; i < E; i++) {
                int p = scanner.nextInt();
                int q = scanner.nextInt();
                int w = scanner.nextInt();
                // 单向边
                graph.computeIfAbsent(p, integer -> new HashMap<>()).put(q, w);
            }

            int source = scanner.nextInt();

            int dest = scanner.nextInt();

            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

            boolean[] vis = new boolean[N + 1];

            queue.add(new int[]{source, 0});

            int[] maxDist = new int[N + 1];
            int[] secDist = new int[N + 1];

            int[] maxCnt = new int[N + 1];
            int[] secCnt = new int[N + 1];

            Arrays.fill(maxDist, Integer.MAX_VALUE);

            maxDist[source] = 0;

            maxCnt[source] = 1;

            while (!queue.isEmpty()) {

                int[] rm = queue.remove();

                int node = rm[0];

                int cnt = maxCnt[node];

                for (Map.Entry<Integer, Integer> entry : graph.getOrDefault(node, Collections.emptyMap()).entrySet()) {

                    Integer next = entry.getKey();

                    Integer w = entry.getValue();

                    int dist = rm[1] + w;

                    if (dist < maxDist[next]) {
                        secDist[next] = maxDist[next];
                        secCnt[next] = maxCnt[next];

                        maxDist[next] = dist;
                        maxCnt[next] = cnt;
                        queue.add(new int[]{next, maxDist[next]});
                    } else if (dist == maxDist[next]) {
                        maxCnt[next] += cnt;
                    } else if (dist < secDist[next]) {
                        secDist[next] = dist;
                        secCnt[next] = cnt;
                        queue.add(new int[]{next, secDist[next]});
                    } else if (dist == secDist[next]) {
                        secCnt[next] += cnt;
                    }

                }

            }

            int ans = maxCnt[dest] + (secDist[dest] == maxDist[dest] + 1 ? secCnt[dest] : 0);

            System.out.println(ans);
        }

    }

}
