package com.zzzj.leet;

import com.zzzj.leet.Leet206.ListNode;

/**
 * @author zzzj
 * @create 2021-09-23 14:46
 */
public class Leet92 {

    public static void main(String[] args) {
        ListNode node = ListNode.build(1, 2, 3, 4, 5);

        ListNode newNode = reverseBetween(node, 2, 4);

        System.out.println(newNode.toString(true));
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode g = dummy;
        ListNode p = g.next;

        for (int i = 0; i < left; i++) {
            g = g.next;
            p = g.next;
        }

        for (int i = 0; i < right - left; i++) {
            ListNode next = p.next;
            ListNode nextNext = null;

            if (next != null) {
                nextNext = next.next;
                next.next = g;
            }

            p.next = nextNext;
            g = next;
        }

        return dummy.next;
    }

}
