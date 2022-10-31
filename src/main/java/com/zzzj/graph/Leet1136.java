package com.zzzj.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Leet1136 {

    public static int minimumSemesters(int n, int[][] relations) {
        List<Integer>[] graph = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] inDegree = new int[n + 1];

        for (int[] relation : relations) {
            graph[relation[0]].add(relation[1]);
            inDegree[relation[1]]++;
        }

        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int ans = 0;
        int size = 0;

        while (!queue.isEmpty()) {
            int sz = queue.size();
            size += sz;
            ans++;
            for (int i = 0; i < sz; i++) {
                Integer first = queue.removeFirst();
                List<Integer> list = graph[first];
                if (list != null) {
                    for (Integer item : list) {
                        if (--inDegree[item] == 0) {
                            queue.addLast(item);
                        }
                    }
                }
            }
        }

        return size == n ? ans : -1;
    }

}
