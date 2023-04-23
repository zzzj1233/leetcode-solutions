package com.zzzj.tree;


import java.util.*;

/**
 * @author zzzj
 * @create 2023-04-23 16:31
 */
public class Leet2641 {

    public static void main(String[] args) {
        System.out.println(replaceValueInTree(TreeNode.buildTree("[5,4,9,1,10,null,7]")).serialize());
    }

    public static TreeNode replaceValueInTree(TreeNode root) {

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();

        Map<TreeNode, Integer> childrenVal = new HashMap<>();

        buildParent(null, root, parentMap, childrenVal);

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {

            int size = queue.size();

            long sum = 0;

            List<TreeNode> nodes = new ArrayList<>(size);

            for (int i = 0; i < size; i++) {

                TreeNode node = queue.removeFirst();

                nodes.add(node);

                sum += node.val;

                if (node.left != null) {
                    queue.addLast(node.left);
                }

                if (node.right != null) {
                    queue.addLast(node.right);
                }

            }

            for (TreeNode node : nodes) {
                TreeNode p = parentMap.get(node);
                if (p == null) {
                    node.val = 0;
                } else {
                    node.val = (int) (sum - childrenVal.get(p));
                }
            }

        }

        return root;
    }

    public static int safeVal(TreeNode node) {
        return node == null ? 0 : node.val;
    }

    public static void buildParent(TreeNode parent, TreeNode current, Map<TreeNode, TreeNode> parentMap, Map<TreeNode, Integer> childrenVal) {
        if (current == null) {
            return;
        }
        parentMap.put(current, parent);
        childrenVal.put(current, safeVal(current.left) + safeVal(current.right));
        buildParent(current, current.left, parentMap, childrenVal);
        buildParent(current, current.right, parentMap, childrenVal);
    }

}
