package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-30 11:04
 */
public class Leet1721 {

    public static ListNode swapNodes(ListNode head, int k) {
        // 找到第K个节点和倒数第K个节点

        ListNode fast = head;
        ListNode slow = head;

        for (int i = 1; i < k; i++) {
            fast = fast.next;
        }

        ListNode kNode = fast;
        fast = fast.next;

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 第K个节点

        int temp = kNode.val;
        kNode.val = slow.val;
        slow.val = temp;

        return head;
    }

}
