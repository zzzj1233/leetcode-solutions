package com.zzzj.leet;

import com.zzzj.leet.Leet206.ListNode;

/**
 * @author zzzj
 * @create 2021-09-26 15:04
 */
public class Leet234 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(ListNode.build(1, 2)));
    }

    public static ListNode compareNode;

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        compareNode = head;
        return compare(head);
    }

    private static boolean compare(ListNode node) {
        boolean result = true;
        if (node.next != null) {
            result = compare(node.next);
        }
        if (!result || node.val != compareNode.val) {
            return false;
        }
        compareNode = compareNode.next;
        return true;
    }

}
