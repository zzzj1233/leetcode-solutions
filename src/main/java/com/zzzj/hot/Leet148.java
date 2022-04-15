package com.zzzj.hot;

import com.zzzj.link.ListNode;

/**
 * @author zzzj
 * @create 2022-04-15 12:27
 */
public class Leet148 {

    public static void main(String[] args) {
        // System.out.println(sortList(ListNode.build(4, 2, 1, 3)).toString(true));
        System.out.println(sortList(ListNode.build(-1, 5, 3, 4, 0)).toString(true));
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return merge(head, findTail(head));
    }

    public static ListNode findTail(ListNode node) {
        ListNode ret = node;

        while (ret.next != null) {
            ret = ret.next;
        }
        return ret;
    }

    public static ListNode findMiddle(ListNode node, ListNode end) {
        ListNode fast = node;
        ListNode slow = node;

        while (fast != end) {
            fast = fast.next;
            if (fast == end) {
                break;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void swap(ListNode node, ListNode node2) {
        int temp = node.val;
        node.val = node2.val;
        node2.val = temp;
    }

    public static ListNode merge(ListNode start, ListNode end) {
        if (start == end) {
            return new ListNode(start.val);
        }
        // base case
        if (start.next == null || start.next == end) {
            if (start.next == null) {
                return new ListNode(start.val);
            }
            if (start.val > end.val) {
                ListNode node = new ListNode(end.val);
                node.next = new ListNode(start.val);
                return node;
            } else {
                ListNode node = new ListNode(start.val);
                node.next = new ListNode(end.val);
                return node;
            }
        }

        // 继续merge
        ListNode mid = findMiddle(start, end);

        ListNode leftStart = merge(start, mid);
        ListNode rightStart = merge(mid.next, end);

        ListNode dummy = new ListNode();

        ListNode result = dummy;

        while (leftStart != null || rightStart != null) {
            if (leftStart == null) {
                result.next = new ListNode(rightStart.val);
                rightStart = rightStart.next;
            } else if (rightStart == null) {
                result.next = new ListNode(leftStart.val);
                leftStart = leftStart.next;
            } else if (leftStart.val < rightStart.val) {
                result.next = new ListNode(leftStart.val);
                leftStart = leftStart.next;
            } else {
                result.next = new ListNode(rightStart.val);
                rightStart = rightStart.next;
            }
            result = result.next;
        }

        // 归并
        return dummy.next;
    }


}
