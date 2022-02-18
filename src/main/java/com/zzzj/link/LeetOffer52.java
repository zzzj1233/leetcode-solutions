package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-30 10:48
 */
public class LeetOffer52 {

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int aLen = 1;
        int bLen = 1;

        ListNode curA = headA;

        ListNode curB = headB;

        while (curA.next != null) {
            aLen++;
            curA = curA.next;
        }

        while (curB.next != null) {
            bLen++;
            curB = curB.next;
        }

        if (curA != curB) {
            return null;
        }

        curA = headA;
        curB = headB;

        if (aLen > bLen) {
            for (int i = 0; i < aLen - bLen; i++) {
                curA = curA.next;
            }
        } else {
            for (int i = 0; i < bLen - aLen; i++) {
                curB = curB.next;
            }
        }

        while (true) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
    }

}
