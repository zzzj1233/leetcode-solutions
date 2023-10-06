package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-10-06 18:58
 */
public class Leet815 {

    public static void main(String[] args) {

        System.out.println(numBusesToDestination(LeetUtils.convertInts("[[1,2,7],[3,6,7]]"), 1, 6));

        System.out.println(numBusesToDestination(LeetUtils.convertInts("[[7,12],[4,5,15],[6],[15,19],[9,12,13]]"), 15, 12));

    }

    public static int numBusesToDestination(int[][] routes, int source, int target) {

        if (source == target)
            return 0;

        int N = routes.length;

        Set[] sets = new Set[N];

        Map<Integer, List<Integer>> indexes = new HashMap<>();

        for (int i = 0; i < N; i++) {
            sets[i] = new HashSet<Integer>(routes[i].length);
            for (int j = 0; j < routes[i].length; j++) {
                sets[i].add(routes[i][j]);
                indexes.computeIfAbsent(routes[i][j], integer -> new ArrayList<>())
                        .add(i);
            }
        }

        if (!indexes.containsKey(source))
            return -1;

        LinkedList<Integer> queue = new LinkedList<>(indexes.get(source));

        Set<Object> visited = new HashSet<>();

        int step = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                Integer index = queue.removeFirst();

                if (sets[index] == null) {
                    continue;
                }

                for (Object item : sets[index]) {

                    Integer val = (Integer) item;

                    if (val.equals(target)) {
                        return step;
                    }

                    if (visited.add(item)) {
                        queue.addAll(indexes.get(val));
                    }
                }

                sets[index] = null;
            }

            step++;
        }

        return -1;
    }

}
