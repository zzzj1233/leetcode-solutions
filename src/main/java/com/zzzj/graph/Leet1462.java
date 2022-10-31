package com.zzzj.graph;

import java.util.*;

public class Leet1462 {

    public static List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Integer>[] graph = new List[numCourses];
        Set<Integer>[] preCourse = new Set[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
            preCourse[i] = new HashSet<>();
        }

        int[] inDegree = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[0]].add(prerequisite[1]);
            inDegree[prerequisite[1]]++;
        }

        LinkedList<Integer> queue = new LinkedList<>();

        // 拓扑排序路径
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }


        while (!queue.isEmpty()) {
            Integer first = queue.removeFirst();

            for (Integer item : graph[first]) {
                inDegree[item]--;

                preCourse[first].add(item);

                for (Integer it : preCourse[first]) {
                    preCourse[it].add(item);
                }

                if (inDegree[item] == 0) {
                    queue.add(item);
                }
            }
        }

        List<Boolean> ans = new ArrayList<>(queries.length);

        for (int[] query : queries) {
            int a = query[0];
            int b = query[1];
            ans.add(preCourse[a].contains(b));
        }

        return ans;
    }

}
