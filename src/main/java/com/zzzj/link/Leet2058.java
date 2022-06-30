package com.zzzj.link;

/**
 * @author Zzzj
 * @create 2021-12-05 17:15
 */
public class Leet2058 {

    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        ListNode prev = head;
        ListNode cur = head;
        ListNode next = head.next;

        ListNode left = null;
        ListNode prevMid = null;
        ListNode mid = null;

        while (next != null) {

            if (cur.val > prev.val && cur.val > next.val) {
                if (left == null) {
                    left = cur;
                } else {
                    prevMid = mid;
                    mid = cur;
                }
            } else if (cur.val < prev.val && cur.val < next.val) {
                if (left == null) {
                    left = cur;
                } else {
                    prevMid = mid;
                    mid = cur;
                }
            }
            prev = cur;
            cur = cur.next;
            next = next.next;
        }


        return null;
    }

}
