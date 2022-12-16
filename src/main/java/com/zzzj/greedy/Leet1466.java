package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-11-25 11:31
 */
public class Leet1466 {

    public static void main(String[] args) {
        System.out.println(minReorder(6, LeetUtils.convertInts("[[0,1],[1,3],[2,3],[4,0],[4,5]]")));
        System.out.println(minReorder(5, LeetUtils.convertInts("[[1,0],[1,2],[3,2],[3,4]]")));
    }

    // 有向图
    public static int minReorder(int n, int[][] connections) {

        Map<Integer, List<Neighbour>> graph = new HashMap<>(n);

        for (int[] connection : connections) {
            int x = connection[0];
            int y = connection[1];

            graph.computeIfAbsent(x, integer -> new ArrayList<>())
                    .add(new Neighbour(y, true));

            graph.computeIfAbsent(y, integer -> new ArrayList<>())
                    .add(new Neighbour(x, false));
        }

        int ans = 0;

        boolean[] visited = new boolean[n];

        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(0);

        visited[0] = true;

        while (!queue.isEmpty()) {
            Integer id = queue.removeFirst();

            List<Neighbour> neighbours = graph.get(id);

            if (neighbours == null) {
                continue;
            }

            for (Neighbour neighbour : neighbours) {
                if (visited[neighbour.id]) {
                    continue;
                }

                visited[neighbour.id] = true;

                if (neighbour.out) {
                    ans++;
                }
                queue.add(neighbour.id);
            }

        }


        return ans;
    }

    private static class Neighbour {
        int id;
        // true  = cur -> id
        // false = id  -> cur
        boolean out;

        public Neighbour(int id, boolean out) {
            this.id = id;
            this.out = out;
        }
    }

}
