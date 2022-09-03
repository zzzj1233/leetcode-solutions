package com.zzzj.tree;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-08-31 18:32
 */
public class Leet987 {

    public static void main(String[] args) {
        System.out.println(verticalTraversal(TreeNode.buildTree("[3,9,20,null,null,15,7]")));
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();

        Map<TreeNode, Integer> level = new HashMap<>();

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        level.put(root, 0);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode first = queue.removeFirst();

                Integer curLevel = level.get(first);

                map.computeIfAbsent(curLevel, integer -> new ArrayList<>())
                        .add(first.val);

                if (first.left != null) {
                    queue.addLast(first.left);
                    level.put(first.left, curLevel - 1);
                }

                if (first.right != null) {
                    queue.addLast(first.right);
                    level.put(first.right, curLevel + 1);
                }

            }
        }

        return new ArrayList<>(map.values());
    }


}
