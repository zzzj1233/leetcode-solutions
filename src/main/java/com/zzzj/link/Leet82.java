package com.zzzj.link;


/**
 * @author zzzj
 * @create 2021-11-04 11:36
 */
public class Leet82 {

    public static void main(String[] args) {
        System.out.println(deleteDuplicates(ListNode.build(1, 2, 3, 3, 4, 4, 5)).toString(true));
//        System.out.println(deleteDuplicates(ListNode.build(1, 1, 1, 2, 3)).toString(true));
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode pprev = new ListNode();
        pprev.next = head;

        ListNode dummy = pprev;

        ListNode prev = head;

        ListNode cur = head.next;

        while (cur != null) {
            // 都要删除
            if (cur.val == prev.val) {
                pprev.next = cur.next;
                prev = cur;
            } else {
                pprev = prev;
            }
            cur = cur.next;
        }

        return dummy.next;
    }

}
