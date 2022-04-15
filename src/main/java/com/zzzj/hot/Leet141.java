package com.zzzj.hot;

import com.zzzj.link.ListNode;

/**
 * @author zzzj
 * @create 2022-04-15 12:14
 */
public class Leet141 {

    public static ListNode hasCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (true) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
            if (fast == null) {
                return null;
            }
            fast = fast.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }

        fast = head;

        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }

}
