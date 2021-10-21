package com.zzzj.leet;

import com.zzzj.leet.Leet206.ListNode;

/**
 * @author zzzj
 * @create 2021-09-23 17:31
 */
public class Leet86 {

    public static void main(String[] args) {
        System.out.println(partition(ListNode.build(1, 4, 3, 2, 5, 2), 3).toString(true));
    }

    public static ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);

        ListNode temp = dummy1;
        ListNode temp2 = dummy2;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode node = dummy.next;

        while (node != null) {
            if (node.val < x) {
                dummy1.next = node;
                dummy1 = dummy1.next;
            } else {
                dummy2.next = node;
                dummy2 = dummy2.next;
            }
            ListNode next = node.next;
            node.next = null;
            node = next;
        }

        dummy1.next = temp2.next;

        return temp.next;
    }

}
