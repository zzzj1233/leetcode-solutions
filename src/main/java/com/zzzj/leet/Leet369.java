package com.zzzj.leet;

import com.zzzj.link.ListNode;

/**
 * @author zzzj
 * @create 2022-05-12 19:07
 */
public class Leet369 {

    public static void main(String[] args) {
        System.out.println(plusOne(ListNode.build(9, 9, 9)).toString(true));
        System.out.println(plusOne(ListNode.build(1, 2, 9)).toString(true));
    }

    public static ListNode plusOne(ListNode head) {
        head = reverse(head);

        boolean add = true;

        ListNode ret = head;

        ListNode pre = null;

        while (head != null) {
            int val = head.val;
            if (val == 9 && add) {
                head.val = 0;
            } else if (add) {
                head.val += 1;
                add = false;
            }
            pre = head;
            head = head.next;
        }

        if (add) {
            pre.next = new ListNode(1);
        }

        return reverse(ret);
    }

    public static ListNode reverse(ListNode head) {
        ListNode pre = head;

        ListNode cur = head.next;

        pre.next = null;

        while (cur != null) {
            ListNode tempNext = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tempNext;
        }

        return pre;
    }

}
