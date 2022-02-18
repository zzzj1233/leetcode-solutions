package com.zzzj.leet;

import com.zzzj.leet.Leet206.ListNode;

/**
 * @author zzzj
 * @create 2021-09-24 14:55
 */
public class Leet21 {

    public static void main(String[] args) {
        System.out.println(mergeTwoLists(ListNode.build(5), ListNode.build(1, 2, 4)).toString(true));
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;

        ListNode next1 = l1;
        ListNode next2 = l2;

        while (true) {
            if (next1 == null && next2 == null) {
                break;
            }
            if (next1 != null && (next2 == null || next1.val < next2.val)) {
                cur.next = new ListNode(next1.val);
                next1 = next1.next;
            } else {
                cur.next = new ListNode(next2.val);
                next2 = next2.next;
            }
            cur = cur.next;
        }

        return dummy.next;
    }

}
