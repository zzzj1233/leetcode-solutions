package com.zzzj.hot;

import com.zzzj.leet.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-04-24 12:20
 */
public class Leet285 {

    public static LinkedList<TreeNode> queue;

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        queue = new LinkedList<>();

        inOrder(root);

        Iterator<TreeNode> iterator = queue.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().val == p.val) {
                return iterator.hasNext() ? iterator.next() : null;
            }
        }

        return null;
    }

    public static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        queue.addLast(node);
        inOrder(node.right);
    }

}
