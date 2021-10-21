package com.zzzj.leet;

import com.zzzj.leet.Leet206.ListNode;

/**
 * @author zzzj
 * @create 2021-09-24 15:18
 */
public class Leet24 {

    public static void main(String[] args) {
        System.out.println(swapPairs(ListNode.build(1, 2, 3, 4)).toString(true));
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode p = dummy;
        ListNode node1 = head;
        ListNode node2 = head.next;

        while (node2 != null) {
            p.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            p = node1;
            node2 = node1.next == null ? null : node1.next.next;
            node1 = node1.next;
        }

        return dummy.next;
    }

}
