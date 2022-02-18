package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-30 10:41
 */
public class Leet2351 {

    public static ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }


        ListNode gte = new ListNode();
        ListNode lt = new ListNode();

        ListNode ltCopy = lt;
        ListNode gteCopy = gte;

        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;

            cur.next = null;

            if (cur.val >= x) {
                gteCopy.next = cur;
                gteCopy = gteCopy.next;
            } else {
                ltCopy.next = cur;
                ltCopy = ltCopy.next;
            }

            cur = next;
        }

        ltCopy.next = gteCopy.next;

        return lt.next;
    }

}
