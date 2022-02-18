package com.zzzj.leet;

import com.zzzj.leet.Leet206.ListNode;

/**
 * @author zzzj
 * @create 2021-09-23 18:02
 */
public class Leet2 {

    public static void main(String[] args) {
        System.out.println(addTwoNumbers(ListNode.build(0), ListNode.build(0)).toString(true));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);

        ListNode cur1 = l1;
        ListNode cur2 = l2;

        ListNode current = dummy;

        int addition = 0;

        while (true) {
            int val1 = 0;
            int val2 = 0;
            if (cur1 != null) {
                val1 = cur1.val;
            }
            if (cur2 != null) {
                val2 = cur2.val;
            }
            if (cur1 == null && cur2 == null && addition == 0) {
                break;
            }
            if (cur1 != null) {
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                cur2 = cur2.next;
            }

            int totalValue = val1 + val2;

            int addValue = totalValue + addition;

            addition = 0;

            if (addValue >= 10) {
                int temp = addValue - 10;
                addition = addValue - temp;
                addition = addition == 10 ? 1 : addition;
                addValue = temp;
            }

            current.next = new ListNode(addValue);
            current = current.next;
        }

        return dummy.next;
    }

}
