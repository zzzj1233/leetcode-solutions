package com.zzzj.leet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-09-23 14:40
 */
public class Leet206 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {

        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public static ListNode build(int... nums) {
            ListNode head = new ListNode(nums[0]);
            ListNode temp = head;

            for (int i = 1; i < nums.length; i++) {
                head.next = new ListNode(nums[i]);
                head = head.next;
            }
            return temp;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }

        public String toString(boolean flag) {
            List<Integer> value = new ArrayList<>();
            ListNode node = this;
            while (node != null) {
                value.add(node.val);
                node = node.next;
            }
            return value.toString();
        }

    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

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
