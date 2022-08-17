package com.zzzj.tree;

import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2022-08-13 14:43
 */
public class Leet662 {

    public static void main(String[] args) {
        System.out.println(widthOfBinaryTree(TreeNode.buildTree("[1,3,2,5,null,null,9,6,null,7]")));
    }

    private static class IndexesNode {
        TreeNode node;
        long index;

        public IndexesNode(TreeNode node, long index) {
            this.node = node;
            this.index = index;
        }
    }

    public static int widthOfBinaryTree(TreeNode root) {
        LinkedList<IndexesNode> queue = new LinkedList<>();

        queue.add(new IndexesNode(root, 1));

        int max = 0;


        while (!queue.isEmpty()) {
            int size = queue.size();

            long start = queue.peekFirst().index;

            for (int i = 0; i < size; i++) {
                IndexesNode indexesNode = queue.removeFirst();
                TreeNode node = indexesNode.node;
                long index = indexesNode.index;

                if (node.left != null) {
                    queue.addLast(new IndexesNode(node.left, (index << 1) - 1));
                }

                if (node.right != null) {
                    queue.addLast(new IndexesNode(node.right, (index << 1)));
                }

                max = (int) Math.max(max, index - start + 1);
            }

        }


        return max;
    }
}
