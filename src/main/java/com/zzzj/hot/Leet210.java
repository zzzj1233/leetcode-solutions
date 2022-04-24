package com.zzzj.hot;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-04-24 11:28
 */
public class Leet210 {

    static class Node {
        int in;
        int val;
        Set<Node> adj;
    }

    // 2
    // []
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        // 0 -> 1
        Map<Integer, Node> graph = new HashMap<>();

        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];

            if (!graph.containsKey(from)) {
                Node node = new Node();
                node.adj = new HashSet<>();
                node.val = from;
                graph.put(from, node);
            }

            if (!graph.containsKey(to)) {
                Node node = new Node();
                node.val = to;
                node.adj = new HashSet<>();
                graph.put(to, node);
            }

            Node fromNode = graph.get(from);
            Node toNode = graph.get(to);

            fromNode.adj.add(toNode);
            toNode.in++;
        }

        LinkedList<Node> queue = graph.values()
                .stream()
                .filter(node -> node.in == 0).collect(Collectors.toCollection(LinkedList::new));

        int size = 0;

        int[] ans = new int[numCourses];

        while (!queue.isEmpty()) {
            Node last = queue.removeFirst();
            ans[size] = last.val;
            size++;
            for (Node node : last.adj) {
                if (--node.in == 0) {
                    queue.add(node);
                }
            }
        }

        if (size != graph.size()){
            return new int[0];
        }

        Set<Integer> keys = graph.keySet();
        for (int i = 0; i < numCourses && size < numCourses; i++) {
            if (!keys.contains(i)) {
                ans[size++] = i;
            }
        }

        return ans;
    }

}
