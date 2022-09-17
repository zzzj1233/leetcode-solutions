package com.zzzj.hot;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-04-24 11:28
 */
public class Leet210 {

    public static void main(String[] args) {
        System.out.println(findOrder(4, LeetUtils.convertInts("[[1,0],[2,0],[3,1],[3,2]]")));
    }

    // 2
    // []
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] inDegree = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[1]].add(prerequisite[0]);
            inDegree[prerequisite[0]]++;
        }

        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }


        int[] ans = new int[numCourses];
        int ansIdx = 0;

        while (!queue.isEmpty()) {
            Integer first = queue.removeFirst();

            List<Integer> adjs = graph[first];

            for (Integer adj : adjs) {
                inDegree[adj]--;
                if (inDegree[adj] == 0) {
                    queue.addLast(adj);
                }
            }

            ans[ansIdx++] = first;
        }

        return ansIdx == numCourses ? ans : new int[0];
    }

}
