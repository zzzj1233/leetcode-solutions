package com.zzzj.hot;

import com.zzzj.leet.LeetUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-04-24 11:08
 */
public class Leet207 {

    public static void main(String[] args) {
        canFinish(2, LeetUtils.convertInts("[[1,0]]"));
    }

    static class Node {
        int in;
        int val;
        Set<Node> adj;
    }

    // 拓扑排序
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
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
        while (!queue.isEmpty()) {
            size++;
            Node last = queue.removeFirst();
            for (Node node : last.adj) {
                if (--node.in == 0) {
                    queue.add(node);
                }
            }
        }

        return size == graph.size();
    }

}
