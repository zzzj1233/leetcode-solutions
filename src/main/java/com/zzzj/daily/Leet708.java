package com.zzzj.daily;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Zzzj
 * @create 2022-06-18 10:45
 */
public class Leet708 {


    private static class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }


    public static Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);
        if (null == head) {
            node.next = node;
            return node;
        }

        TreeMap<Integer, Node> map = new TreeMap<>();

        Set<Node> set = new HashSet<>();

        Node result = head;

        while (set.add(head)) {
            map.put(head.val, head);
            head = head.next;
        }

        Node n = map.lastEntry().getValue();

        Map.Entry<Integer, Node> entry = map.floorEntry(insertVal);

        if (null != entry) {
            n = entry.getValue();
        }

        node.next = n.next;

        n.next = node;

        return head;
    }

    public static void dfs(int val, Node head) {

    }

}
