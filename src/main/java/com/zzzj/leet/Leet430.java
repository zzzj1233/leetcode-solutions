package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-18 11:50
 */
public class Leet430 {

    private static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }


    public static void main(String[] args) {
        final Node node3 = new Node();
        node3.val = 3;

        final Node node2 = new Node();
        node2.val = 2;

        final Node node1 = new Node();
        node1.val = 1;

        node1.child = node2;
        node2.child = node3;

        Node node = flatten(node1);
    }

    // [1,null,2,null,3,null]
    public static Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        Node ans = head;
        dfs(head);
        return ans;
    }

    public static Node dfs(Node node) {
        Node next = node.next;
        if (node.child != null) {
            Node child = node.child;
            node.next = child;
            child.prev = node;
            node.child = null;
            Node childLast = dfs(child);

            childLast = dfs(childLast);

            if (next != null) {
                childLast.next = next;
                next.prev = childLast;
            }
        }
        if (next != null) {
            return dfs(next);
        }
        return node;
    }

}
