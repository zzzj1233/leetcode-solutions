package com.zzzj.hot;

import com.zzzj.link.ListNode;

/**
 * @author zzzj
 * @create 2022-04-15 12:27
 */
public class Leet148 {

    public static void main(String[] args) {
        System.out.println(sortList(ListNode.build(4, 2, 1, 3)).toString(true));
        System.out.println(sortList(ListNode.build(-1, 5, 3, 4, 0)).toString(true));
    }

    public static ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        return split(head, null);
    }

    public static ListNode split(ListNode head, ListNode end) {
        if (head == end) {
            head.next = null;
            return head;
        }

        ListNode middle = findMiddle(head, end);

        if (head == middle) {
            head.next = null;
            return head;
        }

        ListNode left = split(head, middle);

        ListNode right = split(middle, end);

        return merge(left, right);
    }

    public static ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;

        while (true) {
            if (left == null && right == null) {
                cur.next = null;
                return dummy.next;
            } else if (left == null) {
                cur.next = right;
                right = right.next;
            } else if (right == null) {
                cur.next = left;
                left = left.next;
            } else {
                if (left.val < right.val) {
                    cur.next = left;
                    left = left.next;
                } else {
                    cur.next = right;
                    right = right.next;
                }
            }
            cur = cur.next;
        }
    }

    public static ListNode findMiddle(ListNode head, ListNode end) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != end) {
            fast = fast.next;
            if (fast == end) {
                return slow;
            }
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }


}
