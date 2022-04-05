package com.zzzj.hot;

import com.zzzj.link.ListNode;

/**
 * @author Zzzj
 * @create 2022-04-17 22:24
 */
public class Leet160 {


    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int n1 = 1;
        int n2 = 1;

        ListNode node1 = headA;
        ListNode node2 = headB;


        while (node1.next != null) {
            n1++;
            node1 = node1.next;
        }

        while (node2.next != null) {
            n2++;
            node2 = node2.next;
        }

        if (node1 != node2) {
            return null;
        }

        int sub = n1 > n2 ? n1 - n2 : n2 - n1;

        ListNode fast = n1 > n2 ? headA : headB;

        ListNode slow = n1 > n2 ? headB : headA;

        for (int i = 0; i < sub; i++) {
            fast = fast.next;
        }


        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }

}
