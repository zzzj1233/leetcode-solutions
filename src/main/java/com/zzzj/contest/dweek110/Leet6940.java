package com.zzzj.contest.dweek110;

import com.zzzj.link.ListNode;

public class Leet6940 {

    public static void main(String[] args) {

        System.out.println(insertGreatestCommonDivisors(ListNode.build(18, 6, 10, 3)).toString(true));

        System.out.println(insertGreatestCommonDivisors(ListNode.build(7)).toString(true));

        System.out.println(insertGreatestCommonDivisors(ListNode.build(7,9)).toString(true));

    }

    public static ListNode insertGreatestCommonDivisors(ListNode head) {

        ListNode slow = head;

        ListNode fast = head;

        while (fast != null) {
            fast = fast.next;

            if (fast == null) break;

            ListNode slowNext = slow.next;

            ListNode node = new ListNode(gcd(slow.val, fast.val));

            slow.next = node;
            node.next = slowNext;

            slow = slowNext;
        }

        return head;
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
