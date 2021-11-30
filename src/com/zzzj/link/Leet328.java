package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-29 17:22
 */
public class Leet328 {

    public static void main(String[] args) {
        ListNode node = ListNode.build(1, 2, 3, 4, 5);
        oddEvenList(node);
        System.out.println(node.toString(true));
    }

    /**
     * 输入: 1->2->3->4->5->NULL
     * 输出: 1->3->5->2->4->NULL
     */
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;

        ListNode evenStart = head.next;

        while (odd.next != null && odd.next.next != null) {
            ListNode evenNext = even.next.next;

            ListNode oddNext = odd.next.next;

            even.next = evenNext;
            odd.next = oddNext;

            even = even.next;
            odd = odd.next;
        }

        odd.next = evenStart;

        return head;
    }

}
