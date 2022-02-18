package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-12-01 15:03
 */
public class Leet2161 {

    public static void main(String[] args) {
        System.out.println(deleteNode(ListNode.build(4, 5, 1, 9), 9).toString(true));
    }

    public static ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        if (head.val == val) {
            return head.next;
        }

        ListNode prev = head;
        ListNode cur = head.next;

        while (cur != null) {
            if (cur.val == val) {
                prev.next = cur.next;
                break;
            }
            prev = cur;
            cur = cur.next;
        }

        return head;
    }

}
