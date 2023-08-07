package com.zzzj.daily;

import com.zzzj.link.ListNode;

/**
 * @author zzzj
 * @create 2023-07-31 18:14
 */
public class Leet143 {

    public static void main(String[] args) {

        ListNode node = ListNode.build(1, 2, 3, 4, 5, 6);

        reorderList(node);

        System.out.println(node.toString(true));
    }

    // L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2
    public static void reorderList(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;

        int len = 0;

        // 找到middle
        while (fast != null) {
            fast = fast.next;
            len++;
            if (fast == null) break;
            fast = fast.next;
            len++;
            prev = slow;
            slow = slow.next;
        }

        // 断开
//        prev.next = null;

        // 反转
        slow = reverse(slow);

        // 重新链接
        ListNode hd = head;

        while (hd != null) {

            ListNode next1 = hd.next;

            ListNode next2 = null;

            if (slow != null)
                next2 = slow.next;

            hd.next = slow;

            if (slow != null)
                slow.next = next1;

            hd = next1;
            slow = next2;
        }

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
