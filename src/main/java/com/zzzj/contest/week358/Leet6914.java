package com.zzzj.contest.week358;

import com.zzzj.link.ListNode;

public class Leet6914 {

    public static void main(String[] args) {

        System.out.println(doubleIt(ListNode.build(1, 8, 9)).toString(true));

        System.out.println(doubleIt(ListNode.build(9, 9, 9)).toString(true));

        System.out.println(doubleIt(ListNode.build(1, 2, 3, 0)).toString(true));

        System.out.println(doubleIt(ListNode.build("[9,1,9,5,0,5,1,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9]")).toString(true));

    }

    public static ListNode doubleIt(ListNode head) {


        ListNode curr = head;

        StringBuilder builder = new StringBuilder();

        while (curr != null) {
            builder.append(curr.val);
            curr = curr.next;
        }

        String str = builder.reverse().toString();

        if (str.isEmpty()) {
            return new ListNode();
        }

        curr = new ListNode();

        ListNode ans = curr;

        int additional = 0;

        for (int i = 0; i < str.length(); i++) {

            int num = Character.digit(str.charAt(i), 10);

            int n = ((num << 1) + additional) % 10;

            additional = ((num << 1) + additional) / 10;

            curr.val = n;

            if (i == str.length() - 1 && additional == 0)
                curr.next = null;
            else
                curr.next = new ListNode();

            curr = curr.next;
        }

        if (additional != 0)
            curr.val = additional;

        return reverse(ans);
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
