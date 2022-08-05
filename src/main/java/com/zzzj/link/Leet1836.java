package com.zzzj.link;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-08-03 14:36
 */
public class Leet1836 {

    public static void main(String[] args) {
        System.out.println(deleteDuplicatesUnsorted(ListNode.build(1, 2, 3, 2)).toString(true));
        System.out.println(deleteDuplicatesUnsorted(ListNode.build(2, 1, 1, 2)));
        System.out.println(deleteDuplicatesUnsorted(ListNode.build(3, 2, 2, 1, 3, 2, 4)).toString(true));
    }

    public static ListNode deleteDuplicatesUnsorted(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        Map<Integer, Integer> mark = new HashMap<>();

        ListNode node = head;
        ListNode pre = dummy;


        while (node != null) {
            int val = node.val;

            Integer value = mark.get(val);

            if (value == null) {
                mark.put(val, val);
            } else {
                if (value != -1) {
                    mark.put(val, -1);
                }
                pre.next = node.next;
            }

            pre = node;
            node = node.next;
        }

        node = head;
        pre = dummy;

        while (node != null) {
            if (mark.get(node.val) == -1) {
                pre.next = node.next;
            } else{
                pre = node;
            }
            node = node.next;
        }

        return dummy.next;
    }

}
