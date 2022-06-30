package com.zzzj.hot;

import com.zzzj.link.ListNode;

/**
 * @author Zzzj
 * @create 2022-01-15 20:49
 */
public class Leet2 {

    public static void main(String[] args) {
        System.out.println(addTwoNumbers(ListNode.build(6, 7, 4, 8), ListNode.build(4, 6, 8, 9)).toString(true));
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode n1 = l1;
        ListNode n2 = l2;

        ListNode dummy = new ListNode();
        ListNode ans = dummy;

        int add = 0;

        while (n1 != null || n2 != null) {
            int val1 = 0;
            int val2 = 0;

            if (n1 != null) {
                val1 = n1.val;
                n1 = n1.next;
            }
            if (n2 != null) {
                val2 = n2.val;
                n2 = n2.next;
            }

            int sum = val1 + val2 + add;

            add = 0;

            if (sum >= 10) {
                add = sum / 10;
                sum -= 10;
            }

            dummy.next = new ListNode(sum);
            dummy = dummy.next;
        }

        if (add != 0) {
            dummy.next = new ListNode(add);
        }

        return ans.next;
    }


}
