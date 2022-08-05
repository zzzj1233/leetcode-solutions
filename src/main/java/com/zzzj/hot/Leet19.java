package com.zzzj.hot;

import com.zzzj.link.ListNode;

/**
 * @author zzzj
 * @create 2022-01-25 14:23
 */
public class Leet19 {

    public static void main(String[] args) {
        System.out.println(removeNthFromEnd(ListNode.build(1, 2, 3, 4, 5), 2).toString(true));
        System.out.println(removeNthFromEnd(ListNode.build(1, 2, 3, 4, 5), 1).toString(true));
        System.out.println(removeNthFromEnd(ListNode.build(1, 2, 3, 4, 5), 5).toString(true));
        System.out.println(removeNthFromEnd(ListNode.build(1), 1) == null);
        System.out.println(removeNthFromEnd(ListNode.build(1, 2), 1).toString(true));
        System.out.println(removeNthFromEnd(ListNode.build(1, 2), 2).toString(true));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode prev = dummy;

        while (fast != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        prev.next = prev.next.next;

        return dummy.next;
    }

}
