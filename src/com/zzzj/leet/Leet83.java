package com.zzzj.leet;

import com.zzzj.leet.Leet206.ListNode;

/**
 * @author zzzj
 * @create 2021-09-23 17:07
 */
public class Leet83 {

    public static void main(String[] args) {
        System.out.println(deleteDuplicates(ListNode.build(1, 1, 1)).toString(true));
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode cur = head;
        ListNode next = head.next;

        while (next != null) {
            while (next != null && next.val == cur.val) {
                cur.next = next.next;
                next = next.next;
            }
            cur = next;
            if (next != null) {
                next = cur.next;
            }
        }

        return head;
    }

}
