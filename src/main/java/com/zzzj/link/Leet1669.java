package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-25 18:40
 */
public class Leet1669 {

    public static void main(String[] args) {
        mergeInBetween(ListNode.build(0, 1, 2, 3, 4, 5), 3, 4, ListNode.build(1000000, 1000001, 1000002));
    }

    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode beforeA = null;
        ListNode afterB = null;

        int i = 0;

        ListNode head = list1;
        ListNode prev = null;

        while (head != null) {
            prev = head;
            head = head.next;
            i++;
            if (i == a) {
                beforeA = prev;
            } else if (i == b) {
                afterB = head.next;
            }
        }

        beforeA.next = list2;

        ListNode end = list2;

        while (end.next != null){
            end = end.next;
        }

        end.next = afterB;

        return list1;
    }

}
