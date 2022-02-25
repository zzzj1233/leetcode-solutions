package com.zzzj.link;


import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-11-04 11:37
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {

    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListNode node = (ListNode) o;

        if (val != node.val) return false;
        return next != null ? next.equals(node.next) : node.next == null;
    }

    @Override
    public int hashCode() {
        int result = val;
        result = 31 * result + (next != null ? next.hashCode() : 0);
        return result;
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
