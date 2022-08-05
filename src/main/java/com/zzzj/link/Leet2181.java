package com.zzzj.link;

import com.zzzj.tree.AvlTree;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * @author zzzj
 * @create 2022-08-04 19:00
 */
public class Leet2181 {

    public static ListNode mergeNodes(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode node = head.next;

        ListNode dummy = new ListNode();

        ListNode cur = dummy;

        int sum = 0;

        while (node != null) {
            if (node.val == 0) {
                cur.next = new ListNode(sum);
                cur = cur.next;
                sum = 0;
            } else {
                sum += node.val;
            }
            node = node.next;
        }

        return dummy.next;
    }

}
