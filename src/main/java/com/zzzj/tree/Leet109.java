package com.zzzj.tree;

import com.zzzj.leet.TreeNode;
import com.zzzj.link.ListNode;

/**
 * @author zzzj
 * @create 2021-11-09 16:29
 */
public class Leet109 {

    public static void main(String[] args) {
        final TreeNode tree = sortedListToBST(ListNode.build(1, 2, 3, 4, 5), null);
        System.out.println(tree.serialize());
    }

    public static TreeNode sortedListToBST(ListNode head, ListNode end) {

        if (head == null){
            return null;
        }

        if (head == end){
            return null;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        System.out.println("head = " + (head == null ? "null" : head.val));
        System.out.println("end = " + (end == null ? "null" : end.val));

        while (fast != end && fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast == null || fast == end) {
                break;
            }
            fast = fast.next;
        }

        if (slow == null) {
            return null;
        }

        TreeNode node = new TreeNode(slow.val);

        if (slow == head){
            return node;
        }

        node.left = sortedListToBST(head, slow);
        node.right = sortedListToBST(slow.next, end);

        return node;
    }

}
