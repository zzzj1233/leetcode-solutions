package com.zzzj.review;

import com.zzzj.link.ListNode;

/**
 * @author zzzj
 * @create 2023-03-09 17:35
 */
public class Leet19 {

    public static void main(String[] args) {
        System.out.println(removeNthFromEnd(ListNode.build(1), 1).toString(true));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode();
        dummy.next = head;

        // fast先走N步
        ListNode fast = head;
        ListNode slow = head;

        for (int i = 0; i < n && fast != null; i++, fast = fast.next) {

        }

        ListNode prev = dummy;

        while (fast != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        if (prev.next != null) {
            prev.next = prev.next.next;
        }

        return dummy.next;
    }
}
