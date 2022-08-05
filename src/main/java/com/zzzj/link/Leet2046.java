package com.zzzj.link;

/**
 * @author zzzj
 * @create 2022-08-03 16:02
 */
public class Leet2046 {

    public static void main(String[] args) {
        System.out.println(sortLinkedList(ListNode.build("[-1,-3,-6,-8,9,10,13,14]")).toString(true));
    }

    public static ListNode sortLinkedList(ListNode head) {
        ListNode negativeDummy = new ListNode();
        ListNode dummy = new ListNode();

        dummy.next = head;

        ListNode node = head;
        ListNode pre = dummy;

        ListNode negative = negativeDummy;

        while (node != null) {
            if (((node.val >> 31) & 1) == 1) {
                pre.next = node.next;
                negative.next = node;
                negative = negative.next;
            } else {
                pre = node;
            }
            node = node.next;
        }

        if (negativeDummy.next != null) {
            ListNode reverse = reverse(negativeDummy.next);
            negativeDummy.next.next = dummy.next;
            return reverse;
        } else {
            return dummy.next;
        }
    }

    public static ListNode reverse(ListNode node) {
        if (node == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = node;

        while (cur != null && cur.val < 0) {
            ListNode nextNode = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextNode;
        }

        return pre;
    }


}
