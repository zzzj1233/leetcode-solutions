package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-29 11:57
 */
public class Leet2354 {

    public static void main(String[] args) {
        getIntersectionNode(ListNode.build(4, 1, 8, 4, 5), ListNode.build(5, 0, 1, 8, 4, 5));
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null) {
            return null;
        }

        if (headB == null) {
            return null;
        }

        int aLen = 1;
        int bLen = 1;

        ListNode aEnd = headA;

        while (aEnd.next != null) {
            aEnd = aEnd.next;
            aLen++;
        }

        ListNode bEnd = headB;

        while (bEnd.next != null) {
            bEnd = bEnd.next;
            bLen++;
        }

        if (aEnd.val != bEnd.val) {
            return null;
        }

        // 看看哪个链表更长
        ListNode aStart = headA;

        ListNode bStart = headB;

        if (aLen > bLen) {
            for (int i = 0; i < aLen - bLen; i++) {
                aStart = aStart.next;
            }
        } else {
            for (int i = 0; i < bLen - aLen; i++) {
                bStart = bStart.next;
            }
        }


        // 找第一个相同的元素
        while (true) {
            if (aStart.val == bStart.val) {
                return aStart;
            }
            aStart = aStart.next;
            bStart = bStart.next;
        }
    }

}
