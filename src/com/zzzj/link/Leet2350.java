package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-29 14:10
 */
public class Leet2350 {

    public static void main(String[] args) {
        ListNode node = ListNode.build(4, 5, 1, 9);
        deleteNode(node);
        System.out.println(node.toString(true));
    }

    public static void deleteNode(ListNode node) {

        ListNode dummy = new ListNode();
        dummy.next = node;
        ListNode prev = dummy;
        while (node.next != null) {
            node.val = node.next.val;
            prev = node;
            node = node.next;
            if (node.next == null) {
                prev.next = null;
                break;
            }
        }

    }

}
