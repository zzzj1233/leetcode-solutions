package com.zzzj.link;

import java.util.LinkedList;

public class Leet2487 {

    public static void main(String[] args) {
        System.out.println(removeNodes(ListNode.build("[5,2,13,3,8]")).toString(true));
    }

    public static ListNode removeNodes(ListNode head) {

        LinkedList<Integer> stack = new LinkedList<>();

        ListNode node = head;

        while (node != null) {
            int value = node.val;
            while (!stack.isEmpty() && value > stack.peekLast()) {
                stack.removeLast();
            }
            stack.add(value);
            node = node.next;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode prev = dummy;
        ListNode cur = head;

        while (cur != null) {
            int val = cur.val;
            if (val >= stack.peekFirst()) {
                stack.removeFirst();
                prev = cur;
            } else {
                prev.next = cur.next;
            }
            cur = cur.next;
        }

        return dummy.next;
    }

}
