package com.zzzj.leet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-10-20 15:47
 */
public class Leet199 {

    public static void main(String[] args) {
        System.out.println(rightSideView(TreeNode.buildTree("[1,1,3,4]")));
    }

    private static List<Integer> res;

    private static class TreeNodeWrp {
        private final TreeNode node;
        private final int level;

        private TreeNodeWrp(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }

    }

    private static void bfs(TreeNode root) {
        LinkedList<TreeNodeWrp> queue = new LinkedList<>();
        queue.add(new TreeNodeWrp(root, 1));

        while (!queue.isEmpty()) {
            TreeNodeWrp first = queue.removeFirst();
            int level = first.level;

            boolean addAble = res.size() < level;

            if (addAble) {
                res.add(first.node.val);
            }

            if (first.node.right != null) {
                queue.add(new TreeNodeWrp(first.node.right, level + 1));
            }

            if (first.node.left != null) {
                queue.add(new TreeNodeWrp(first.node.left, level + 1));
            }

        }
    }

    public static List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        res = new ArrayList<>();

        res.add(root.val);

        bfs(root);

        return res;
    }

}
