package com.zzzj.leet;

import com.zzzj.leet.Leet206.ListNode;

/**
 * @author zzzj
 * @create 2021-09-26 15:04
 */
public class Leet234 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(ListNode.build(1, 2, 2, 1)));
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        int size = 0;

        while (fast != null) {
            size++;
            fast = fast.next;
            if (fast == null) {
                break;
            }
            size++;
            fast = fast.next;
            slow = slow.next;
        }

        boolean even = size % 2 == 0;

        ListNode reversed = reverse(even ? slow : slow.next);

        ListNode cur = head;

        while (cur != slow) {
            if (cur.val != reversed.val) {
                return false;
            }
            cur = cur.next;
            reversed = reversed.next;
        }

        return true;
    }

    public static ListNode reverse(ListNode node) {
        ListNode prev = null;
        ListNode cur = node;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }


}
