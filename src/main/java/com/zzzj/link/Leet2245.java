package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-29 15:58
 */
public class Leet2245 {

    public static void main(String[] args) {
        ListNode node = ListNode.build(1, 2, 3, 4);

        reorderList(node);

        System.out.println(node.toString(true));
    }

    // 1. 找到中间节点
    // 2. 翻转后半段链表
    // 3. 链接
    public static void reorderList(ListNode head) {
        if (head == null) {
            return;
        }

        ListNode fast = head;
        ListNode slow = head;

        // 1. 找到中间节点
        while (true) {
            fast = fast.next;
            if (fast == null) {
                break;
            }
            fast = fast.next;
            if (fast == null) {
                break;
            }
            slow = slow.next;
        }

        // 2. 翻转后半段链表
        slow = reverse(slow);

        // 3. 链接
        ListNode cur = head;

        while (cur != slow && cur != null) {
            ListNode next = cur.next;
            ListNode slowNext = slow.next;
            cur.next = slow;
            slow.next = next;
            slow = slowNext;
            cur = next;
        }

    }

    private static ListNode reverse(ListNode node) {
        ListNode prev = null;
        ListNode cur = node;
        ListNode next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

}
