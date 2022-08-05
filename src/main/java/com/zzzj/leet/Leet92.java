package com.zzzj.leet;

import com.zzzj.leet.Leet206.ListNode;

/**
 * @author zzzj
 * @create 2021-09-23 14:46
 */
public class Leet92 {

    public static void main(String[] args) {

//        System.out.println(reverseBetween(ListNode.build(1, 2, 3, 4, 5), 2, 4).toString(true));
//        System.out.println(reverseBetween(ListNode.build(1, 2, 3, 4, 5), 4, 5).toString(true));
        System.out.println(reverseBetween(ListNode.build(3, 5), 1, 2).toString(true));

    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {

        if (left == right) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode prev = dummy;
        ListNode cur = head;

        int index = 1;

        while (cur != null) {

            if (index == left) {
                ListNode pp = prev;
                ListNode start = cur;
                // 开始反转
                prev = cur;
                cur = cur.next;
                index++;

                while (index <= right) {
                    ListNode next = cur.next;
                    cur.next = prev;
                    prev = cur;
                    cur = next;
                    index++;
                }

                pp.next = prev;
                start.next = cur;

                return dummy.next;
            }
            index++;
            prev = cur;
            cur = cur.next;
        }

        return dummy.next;
    }

}
