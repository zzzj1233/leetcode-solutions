package com.zzzj.link;

import java.util.Stack;

/**
 * @author zzzj
 * @create 2021-11-30 17:36
 */
public class LeetOffer25 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        ListNode cur = l1;

        while (cur != null) {
            stack1.add(cur.val);
            cur = cur.next;
        }

        cur = l2;

        while (cur != null) {
            stack1.add(cur.val);
            cur = cur.next;
        }


        return null;
    }

}
