package com.zzzj.leet;

import com.zzzj.leet.Leet206.ListNode;

/**
 * @author zzzj
 * @create 2021-09-24 14:44
 */
public class Leet203 {

    public static void main(String[] args) {
        System.out.println(removeElements(ListNode.build(1, 2, 6, 3, 4, 5, 6), 6).toString(true));
    }

    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode next = dummy.next;
        ListNode prev = dummy;

        while (next != null) {
            if (next.val == val) {
                prev.next = next.next;
                next = next.next;
            } else {
                prev = next;
                next = next.next;
            }
        }
        return dummy.next;
    }

}
