package com.zzzj.leet;

import com.zzzj.leet.Leet206.ListNode;

/**
 * @author zzzj
 * @create 2021-09-24 16:31
 */
public class Leet147 {

    public static void main(String[] args) {
        System.out.println(insertionSortList(ListNode.build(4, 2, 1, 3)).toString(true));
    }

    public static ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode cur = dummy.next;

        ListNode next = cur == null ? null : cur.next;

        ListNode last = cur;

        ListNode retNode = head;

        while (next != null) {
            // 找到插入的位置
            if (next.val < last.val) {
                ListNode temp = retNode;
                ListNode prev = null;
                while (next.val > temp.val) {
                    prev = temp;
                    temp = temp.next;
                }
                if (prev == null) {
                    retNode = next;
                    ListNode tempNext = next.next;
                    next.next = temp;
                    last.next = tempNext;
                    next = tempNext;
                } else {
                    ListNode tempNext = next.next;
                    next.next = prev.next;
                    prev.next = next;
                    last.next = tempNext;
                    next = tempNext;
                }
            } else {
                last = next;
                next = next.next;
            }
        }

        return retNode;
    }

}