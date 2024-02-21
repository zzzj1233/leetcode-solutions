package com.zzzj.acw;

import java.util.*;

/**
 * @author zzzj
 * @create 2024-02-05 16:45
 */
public class Q1134 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int E = scanner.nextInt();

        final int MOD = 100003;

        Map<Integer, List<Integer>> graph = new HashMap<>(N);

        for (int i = 0; i < E; i++) {
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            graph.computeIfAbsent(p, integer -> new ArrayList<>()).add(q);
            graph.computeIfAbsent(q, integer -> new ArrayList<>()).add(p);
        }

        LinkedList<Integer> queue = new LinkedList<>();

        int[] dist = new int[N + 1];
        int[] cnt = new int[N + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[1] = 0;
        cnt[1] = 1;

        queue.add(1);

        while (!queue.isEmpty()) {

            Integer node = queue.removeFirst();

            for (Integer next : graph.getOrDefault(node, Collections.emptyList())) {

                if (dist[node] + 1 < dist[next]) {
                    queue.addLast(next);
                    dist[next] = dist[node] + 1;
                    cnt[next] = cnt[node];
                } else if (dist[node] + 1 == dist[next]) {
                    cnt[next] = (cnt[next] + cnt[node]) % MOD;
                }

            }

        }

        for (int i = 1; i <= N; i++) {
            System.out.println(cnt[i]);
        }

    }

}
