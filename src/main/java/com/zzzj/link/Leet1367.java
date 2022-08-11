package com.zzzj.link;

import com.zzzj.tree.TreeNode;

/**
 * @author Zzzj
 * @create 2022-08-04 21:05
 */
public class Leet1367 {

    public static void main(String[] args) {
        System.out.println(isSubPath(ListNode.build(1, 10), TreeNode.buildTree("[1,null,1,10,1,9]")));
    }

    public static boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }

        if (root.val == head.val) {
            if (dfs(head, root)) {
                return true;
            }
        }

        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    public static boolean dfs(ListNode node, TreeNode root) {
        if (node == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (root.val != node.val) {
            return false;
        }
        return dfs(node.next, root.left) || dfs(node.next, root.right);
    }

}
