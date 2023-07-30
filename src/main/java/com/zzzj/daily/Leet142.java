package com.zzzj.daily;

import com.zzzj.link.ListNode;

public class Leet142 {

    public static void main(String[] args) {
        ListNode node3 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node0 = new ListNode(0);
        ListNode node4 = new ListNode(4);

        node3.next = node2;
        node2.next = node0;
        node0.next = node4;
        node4.next = node2;

        System.out.println(detectCycle(node3));
    }

    public static ListNode detectCycle(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null) {
            fast = fast.next;
            if (fast == null) return null;
            fast = fast.next;
            slow = slow.next;

            if (fast == slow) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }

        return null;
    }

}
