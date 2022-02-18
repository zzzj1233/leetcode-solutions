package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-12 17:18
 */
public class Leet993 {


    private static int xLevel;
    private static int yLevel;

    private static TreeNode xParent;
    private static TreeNode yParent;

    public static void main(String[] args) {
        System.out.println(isCousins(TreeNode.buildTree("[1,2,3,null,4,null,5]"), 5, 4));
    }

    // 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点
    public static boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        xLevel = yLevel = 0;
        xParent = yParent = null;

        dfs(root, 0, null, x, y);

        return xLevel == yLevel && xParent != yParent;
    }

    private static void dfs(TreeNode root, int level, TreeNode parent, int x, int y) {
        if (root.left != null) {
            dfs(root.left, level + 1, root, x, y);
        }

        if (root.right != null) {
            dfs(root.right, level + 1, root, x, y);
        }

        if (root.val == x) {
            xLevel = level;
            xParent = parent;
        } else if (root.val == y) {
            yLevel = level;
            yParent = parent;
        }

    }

}
