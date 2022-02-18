package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-30 12:05
 */
public class Leet2246 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(ListNode.build(1, 2, 3, 3, 2, 1)));
    }

    public static boolean isPalindrome(ListNode head) {
        // 1. 找到中间节点
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null) {
            fast = fast.next;
            if (fast == null) {
                break;
            }
            fast = fast.next;
            slow = slow.next;
        }

        ListNode originSlow = slow;
        // 2. 翻转后半段链表
        slow = reverseList(slow);

        // 3. 逐一比较
        ListNode cur = head;

        ListNode mid = slow;

        while (cur != originSlow) {
            if (cur.val != mid.val) {
                return false;
            }
            mid = mid.next;
            cur = cur.next;
        }

        return true;
    }

    private static ListNode reverseList(ListNode node) {
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
