package com.zzzj.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-08-15 17:40
 */
public class Leet742 {

    public static int findClosestLeaf(TreeNode root, int k) {
        Map<Integer, TreeNode> parentMap = new HashMap<>();

        buildGraph(root, null, parentMap);

        boolean[] visited = new boolean[1001];

        TreeNode _parent = parentMap.get(k);

        TreeNode curNode = _parent == null ? root : _parent.left != null && _parent.left.val == k ? _parent.left : _parent.right;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(curNode);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode first = queue.removeFirst();

                visited[first.val] = true;

                if (first.left == null && first.right == null) {
                    return first.val;
                }

                if (first.left != null && !visited[first.left.val]) {
                    queue.addLast(first.left);
                }

                if (first.right != null && !visited[first.right.val]) {
                    queue.addLast(first.right);
                }

                TreeNode parent = parentMap.get(first.val);

                if (parent != null && !visited[parent.val]) {
                    queue.addLast(parent);
                }
            }
        }

        return -1;
    }

    public static void buildGraph(TreeNode root, TreeNode parent, Map<Integer, TreeNode> parentMap) {
        if (root == null) {
            return;
        }

        parentMap.put(root.val, parent);

        buildGraph(root.left, root, parentMap);
        buildGraph(root.right, root, parentMap);
    }

}
