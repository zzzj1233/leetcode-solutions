package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-04 11:36
 */
public class Leet82 {

    public static void main(String[] args) {
        System.out.println(deleteDuplicates(ListNode.build(1, 2, 3, 3, 4, 4, 5)).toString(true));
        System.out.println(deleteDuplicates(ListNode.build(1, 1, 1, 2, 3)).toString(true));
        System.out.println(deleteDuplicates(ListNode.build(1, 2)).toString(true));
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode prev = dummy;
        ListNode cur = head;

        while (cur != null) {
            if (cur.next != null && cur.next.val == cur.val) {
                while (cur.next != null && cur.next.val == cur.val) {
                    cur = cur.next;
                }
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }

        return dummy.next;
    }

}
