package com.zzzj.link;

/**
 * @author zzzj
 * @create 2022-08-03 16:49
 */
public class Leet2130 {

    public static void main(String[] args) {
        System.out.println(pairSum(ListNode.build("[1,2,3,8,9,10]")));
    }

    public static int pairSum(ListNode head) {
        if (head == null) {
            return 0;
        }

        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;

        while (fast != null) {
            fast = fast.next;
            if (fast == null) {
                break;
            }
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        ListNode reversed = reverseList(slow);

        ListNode node = head;

        ListNode end = prev.next;

        int ans = 0;

        while (reversed != null) {
            ans = Math.max(ans, node.val + reversed.val);
            node = node.next;
            reversed = reversed.next;
        }

        return ans;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode next = null;
        ListNode prev = null;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

}
