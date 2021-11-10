package com.zzzj.tree;

import com.zzzj.leet.LeetUtils;
import com.zzzj.leet.TreeNode;
import com.zzzj.link.ListNode;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-11-09 16:29
 */
public class Leet109 {

    public static void main(String[] args) {
        int[] ints = LeetUtils.newArray(10, 100);
        Arrays.sort(ints);
        System.out.println(Arrays.toString(ints));
        TreeNode treeNode = sortedListToBST(ListNode.build(ints));
        System.out.println("~");
    }

    private static TreeNode leftRotate(TreeNode root) {
        TreeNode right = root.right;
        root.right = null;
        right.left = root;
        return right;
    }

    private static TreeNode rightRotate(TreeNode root) {
        TreeNode left = root.left;
        left.right = root;
        root.left = null;
        return left;
    }

    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        TreeNode root = new TreeNode(head.val);

        TreeNode answer = null;

        ListNode node = head.next;

        int leftHeight = 0;

        int rightHeight = 0;

        boolean appendRight = false;

        while (node != null) {
            TreeNode newNode = new TreeNode(node.val);

            if (appendRight) {
                root.right = newNode;
                root = newNode;
                rightHeight++;
            } else {
                newNode.left = root;
                root = newNode;
                leftHeight++;
            }

            // 右旋
            if (leftHeight - rightHeight > 1) {
                root = rightRotate(root);
                leftHeight = rightHeight = 1;
                appendRight = true;
                answer = root;
                root = root.right;
            } else if (rightHeight - leftHeight > 1) {
                answer = leftRotate(answer);
                leftHeight = rightHeight = 1;
            }

            node = node.next;
        }

        return answer == null ? root : answer;
    }

}
