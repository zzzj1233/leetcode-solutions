package com.zzzj.link;

/**
 * @author Zzzj
 * @create 2021-12-05 16:58
 */
public class Leet142 {

    public static void main(String[] args) {
        ListNode listNode3 = new ListNode(3);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode0 = new ListNode(0);
        ListNode listNode4 = new ListNode(4);
        listNode3.next = listNode2;
        listNode2.next = listNode0;
        listNode0.next = listNode4;
        listNode4.next = listNode2;

        detectCycle(listNode3);
    }

    public static ListNode detectCycle(ListNode head) {
        // 第一个相交点
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (true) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
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
