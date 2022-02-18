package com.zzzj.leet;

import com.zzzj.leet.Leet206.ListNode;

/**
 * @author zzzj
 * @create 2021-09-24 15:33
 */
public class Leet25 {

    public static void main(String[] args) {
        System.out.println(reverseKGroup(ListNode.build(1, 2, 3, 4, 5, 6, 7, 8), 2).toString(true));
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        int index = 0;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        ListNode revNode = dummy;
        ListNode next = null;
        ListNode prev = null;

        while (cur != null) {
            next = cur.next;
            prev = cur;
            if (index > 0 && index % k == 0) {
                ListNode temp = revNode.next;
                revNode.next = reverseNode(revNode.next, k);
                revNode = temp;
            }
            index++;
            cur = next;
        }

        return dummy.next;
    }

    private static ListNode reverseNode(ListNode node, int end) {
        if (node == null) {
            return null;
        }

        ListNode cur = node;
        ListNode prev = null;
        ListNode next = null;

        int i = 0;

        while (cur != null && i < end) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            i++;
        }

        node.next = next;

        return prev;
    }

}
