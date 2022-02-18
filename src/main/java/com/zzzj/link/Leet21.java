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
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode node1 = list1;
        ListNode node2 = list2;

        ListNode answer;

        if (node1.val <= node2.val) {
            answer = node1;
            node1 = node1.next;
        } else {
            answer = node2;
            node2 = node2.next;
        }

        ListNode answerC = answer;

        while (true) {
            if (node1 == null) {
                answer.next = node2;
                break;
            }
            if (node2 == null) {
                answer.next = node1;
                break;
            }
            if (node1.val <= node2.val) {
                ListNode next = node1.next;
                answer.next = node1;
                node1 = next;
            } else {
                ListNode next = node2.next;
                answer.next = node2;
                node2 = next;
            }
            answer = answer.next;
        }

        return answerC;
    }

}
