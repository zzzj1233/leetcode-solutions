package com.zzzj.design;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-10-24 10:37
 */
public class Leet2102 {

    static SORTracker tracker = new SORTracker();

    public static void main(String[] args) {
        check1();

        check2();
    }

    public static void check2() {
        tracker = new SORTracker();

        add(56);

        check("56");

        add(93);

        add(32);

        check("56");
    }

    public static void check1() {
        tracker = new SORTracker();
        // bradford:2 , branford: 3 / empty
        tracker.add("bradford", 2);
        tracker.add("branford", 3);

        // bradford:2  / branford: 3
        check("branford");

        // bradford:2 , alps:2 / branford: 3
        tracker.add("alps", 2);

        // bradford:2 / alps:2 , branford: 3
        check("alps");

        // orland: 2 , bradford:2 / alps:2 , branford: 3
        tracker.add("orland", 2);
        // orland: 2 / alps:2 , bradford:2 , branford: 3
        check("bradford");

        tracker.add("orlando", 3);
        check("bradford");
    }

    public static void add(int score) {
        tracker.add(String.valueOf(score), score);
    }

    public static void check(String expect) {
        String actually = tracker.get();

        if (!expect.equals(actually))
            throw new IllegalStateException(String.format("Expect : %s , Actually : %s", expect, actually));
    }

    private static class SORTracker {

        PriorityQueue<Node> minQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.score == o2.score)
                    return o2.name.compareTo(o1.name);
                return o1.score - o2.score;
            }
        });

        PriorityQueue<Node> maxQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.score == o2.score)
                    return o1.name.compareTo(o2.name);
                return o2.score - o1.score;
            }
        });

        public SORTracker() {

        }

        public void add(String name, int score) {
            Node node = new Node(name, score);

            if (!minQueue.isEmpty() && (node.score > minQueue.peek().score || (node.score == minQueue.peek().score && node.name.compareTo(minQueue.peek().name) < 0))) {
                minQueue.add(node);
                maxQueue.add(minQueue.remove());
            } else {
                maxQueue.add(node);
            }
        }

        public String get() {
            if (maxQueue.isEmpty())
                return null;

            Node remove = maxQueue.remove();

            minQueue.add(remove);

            return remove.name;
        }

        private static class Node {
            String name;
            int score;

            public Node(String name, int score) {
                this.name = name;
                this.score = score;
            }
        }
    }

}
