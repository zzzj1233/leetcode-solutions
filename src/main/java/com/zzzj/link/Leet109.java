package com.zzzj.link;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-25 14:45
 */
public class Leet109 {

    public static void main(String[] args) {
        System.out.println(sortedListToBST(ListNode.build(-10, -3, 0, 5, 9)).serialize());
    }

    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        return buildTreeNode(head, null);
    }

    private static TreeNode buildTreeNode(ListNode head, ListNode end) {
        if (head == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        // fast走两步, slow走一步
        while (fast != end) {
            fast = fast.next;
            if (fast == end) {
                break;
            }
            fast = fast.next;
            slow = slow.next;
        }

        if (slow == null) {
            return null;
        }

        if (fast == slow) {
            return new TreeNode(slow.val);
        }

        TreeNode root = new TreeNode(slow.val);
        root.left = buildTreeNode(head, slow);
        root.right = buildTreeNode(slow.next, end);

        return root;
    }

}
