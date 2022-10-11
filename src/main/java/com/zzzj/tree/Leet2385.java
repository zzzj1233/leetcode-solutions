package com.zzzj.tree;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-10-10 14:50
 */
public class Leet2385 {

    public static void main(String[] args) {
        System.out.println(amountOfTime(TreeNode.buildTree("[1,5,3,null,4,10,6,9,2]"), 3));
        System.out.println(amountOfTime(TreeNode.buildTree("[1]"), 1));
    }

    public static int amountOfTime(TreeNode root, int start) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();

        Map<Integer, TreeNode> map = new HashMap<>();

        collect(parentMap, map, root, null);

        LinkedList<TreeNode> queue = new LinkedList<>();

        int ans = 0;

        queue.add(map.get(start));

        Set<Integer> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();

                visited.add(node.val);

                TreeNode p = parentMap.get(node);
                TreeNode l = node.left;
                TreeNode r = node.right;

                if (p != null && !visited.contains(p.val)) {
                    queue.addLast(p);
                }

                if (l != null && !visited.contains(l.val)) {
                    queue.addLast(l);
                }

                if (r != null && !visited.contains(r.val)) {
                    queue.addLast(r);
                }

            }

            ans++;
        }

        return ans - 1;
    }

    public static void collect(Map<TreeNode, TreeNode> parentMap, Map<Integer, TreeNode> map, TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        map.put(node.val, node);
        parentMap.put(node, parent);
        collect(parentMap, map, node.left, node);
        collect(parentMap, map, node.right, node);
    }

}
