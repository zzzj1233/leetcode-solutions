package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-25 18:52
 */
public class LeetOffer23 {

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode node1 = headA;
        ListNode node2 = headB;

        while (node1 != null && node2 != null) {
            if (node1 == node2) {
                return node1;
            }
            node1 = node1.next;
            node2 = node2.next;
        }

        return null;
    }

}
