package com.zzzj.leet;

import com.zzzj.leet.Leet206.ListNode;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2021-09-23 18:41
 */
public class Leet445 {

    public static void main(String[] args) {
        System.out.println(addTwoNumbers(ListNode.build(9), ListNode.build(9)).toString(true));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        LinkedList<Integer> linkedList1 = new LinkedList<>();
        LinkedList<Integer> linkedList2 = new LinkedList<>();

        ListNode l1Next = l1;
        ListNode l2Next = l2;

        while (true) {
            int l1Val = 0;
            int l2Val = 0;

            if (l1Next != null) {
                l1Val = l1Next.val;
            }
            if (l2Next != null) {
                l2Val = l2Next.val;
            }
            if (l1Next == null && l2Next == null) {
                break;
            }

            if (l1Next == null) {
                linkedList2.addLast(l2Val);
                linkedList1.addFirst(0);
            } else if (l2Next == null) {
                linkedList1.addLast(l1Val);
                linkedList2.addFirst(0);
            } else {
                linkedList1.add(l1Val);
                linkedList2.add(l2Val);
            }

            l1Next = l1Next != null ? l1Next.next : null;
            l2Next = l2Next != null ? l2Next.next : null;
        }


        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int addition = 0;

        while (!linkedList1.isEmpty() || addition != 0) {
            int addValue = 0;

            if (!linkedList1.isEmpty()) {
                Integer val1 = linkedList1.removeLast();
                Integer val2 = linkedList2.removeLast();
                addValue = val1 + val2;
            }

            addValue += addition;
            addition = 0;

            if (addValue >= 10) {
                addition = addValue / 10;
                addValue -= 10;
            }

            ListNode node = new ListNode(addValue);
            node.next = cur.next;
            cur.next = node;
        }

        return dummy.next;
    }

}
