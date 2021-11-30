package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-25 12:22
 */
public class LeetOffer22 {

    // 倒数第K个节点
    public static ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;

        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

}
