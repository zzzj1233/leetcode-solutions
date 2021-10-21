package com.zzzj.leet;

import com.zzzj.leet.Leet206.ListNode;

/**
 * @author zzzj
 * @create 2021-09-24 18:41
 */
public class Leet19 {

    public static void main(String[] args) {
        System.out.println(removeNthFromEnd(ListNode.build(1), 1).toString(true));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode temp = head;
        ListNode fast = head;
        ListNode cur = head;
        ListNode prev = null;

        for (int i = 1; i < n; i++) {
            fast = fast.next;
        }

        while (cur != null) {
            fast = fast.next;
            if (fast == null) {
                if (prev == null) {
                    return cur.next;
                }
                prev.next = cur.next;
                break;
            }
            prev = cur;
            cur = cur.next;
        }

        return temp;
    }

}
