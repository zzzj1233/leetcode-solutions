package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zzzj
 * @create 2023-10-23 16:59
 */
public class Leet2092 {

    public static void main(String[] args) {

        System.out.println(findAllPeople(
                4,
                LeetUtils.convertInts("[[3,1,3],[1,2,2],[0,3,3]]"),
                3
        ));

        System.out.println(findAllPeople(
                6,
                LeetUtils.convertInts("[[1,2,5],[2,3,8],[1,5,10]]"),
                1
        ));

    }

    public static List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {

        boolean[] known = new boolean[n];

        known[0] = true;
        known[firstPerson] = true;

        Map<Integer, List<Node>> graph = new HashMap<>();

        for (int[] meeting : meetings) {
            graph.computeIfAbsent(meeting[0], integer -> new ArrayList<>()).add(new Node(meeting[0], meeting[1], meeting[2]));
            graph.computeIfAbsent(meeting[1], integer -> new ArrayList<>()).add(new Node(meeting[1], meeting[0], meeting[2]));
        }

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));

        queue.addAll(graph.getOrDefault(0, Collections.emptyList()));

        queue.addAll(graph.getOrDefault(firstPerson, Collections.emptyList()));

        while (!queue.isEmpty()) {

            Node node = queue.remove();

            if (!known[node.other]) {
                known[node.other] = true;
                queue.addAll(graph.get(node.other).stream().filter(m -> m.time >= node.time).collect(Collectors.toList()));
            }

        }

        return IntStream.range(0, known.length)
                .filter(index -> known[index])
                .boxed()
                .collect(Collectors.toList());
    }

    private static class Node {
        int self;
        int other;
        int time;

        public Node(int self, int other, int time) {
            this.self = self;
            this.other = other;
            this.time = time;
        }
    }
}
