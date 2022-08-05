package com.zzzj.link;

/**
 * @author zzzj
 * @create 2022-08-03 16:31
 */
public class Leet2095 {

    public static void main(String[] args) {
        System.out.println(deleteMiddle(ListNode.build(1, 3, 4, 7, 1, 2, 6)).toString(true));
        System.out.println(deleteMiddle(ListNode.build(1, 2, 3, 4)).toString(true));
        System.out.println(deleteMiddle(ListNode.build(2, 1)).toString(true));
        System.out.println(deleteMiddle(ListNode.build(2)).toString(true));
    }

    public static ListNode deleteMiddle(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;

        while (fast != null) {
            fast = fast.next;
            if (fast == null) {
                break;
            }
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        if (prev == null) {
            return null;
        }

        prev.next = prev.next.next;

        return head;
    }

}
