package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-25 12:23
 */
public class LeetOffer24 {

    // 反转链表
    public static ListNode reverseList(ListNode head) {
        // [1,2,3,4,5] -> [5,4,3,2,1]

        if (head == null) {
            return null;
        }

        ListNode prev = null;
        ListNode cur = head;
        ListNode next;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

}
