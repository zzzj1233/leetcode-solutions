package com.zzzj.leet;

import com.zzzj.leet.Leet206.ListNode;

/**
 * @author zzzj
 * @create 2021-09-26 10:10
 */
public class Leet61 {

    public static void main(String[] args) {
        System.out.println(rotateRight(ListNode.build(1, 2), 1).toString(true));
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int i = 0;

        ListNode fast = head;
        ListNode slow = head;

        while (i < k + 1) {
            if (fast == null) {
                fast = head;
                k %= i;
                i = 0;
            }
            fast = fast.next;
            i++;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        if (slow.next == null) {
            return head;
        }

        ListNode res = slow.next;

        // 切断
        ListNode tempCur = slow;

        while (tempCur.next != null) {
            tempCur = tempCur.next;
        }

        tempCur.next = head;

        slow.next = null;

        return res;
    }


}
