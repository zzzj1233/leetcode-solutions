package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-12-01 11:10
 */
public class Leet21 {

    public static void main(String[] args) {
        System.out.println(mergeTwoLists(ListNode.build(1, 2, 4), ListNode.build(1, 3, 4)).toString(true));
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();

        ListNode cur = dummy;

        while (list1 != null || list2 != null) {
            if (list1 == null) {
                cur.next = list2;
                break;
            }
            if (list2 == null) {
                cur.next = list1;
                break;
            }
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }

            cur = cur.next;
        }

        return dummy.next;
    }

}
