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

        ListNode node1 = headA;
        ListNode node2 = headB;

        int count1 = 0;
        int count2 = 0;

        while (node1 != null) {
            count1++;
            node1 = node1.next;
        }

        while (node2 != null) {
            count2++;
            node2 = node2.next;
        }

        node1 = headA;
        node2 = headB;

        while (count1 > count2) {
            node1 = node1.next;
            count1--;
        }

        while (count2 > count1) {
            node2 = node2.next;
            count2--;
        }

        while (node1 != node2 && node1 != null) {
            node1 = node1.next;
            node2 = node2.next;
        }

        return node1;
    }

}
