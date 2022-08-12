package com.zzzj.tree;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-08-05 15:03
 */
public class Leet314 {

    public static List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        LinkedList<TreeNode> queue = new LinkedList<>();

        Map<TreeNode, Integer> map = new HashMap<>();

        TreeMap<Integer, List<Integer>> listMap = new TreeMap<>();

        map.put(root, 1);

        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode first = queue.removeFirst();

                Integer position = map.get(first);

                listMap.computeIfAbsent(position, ignore -> new ArrayList<>())
                        .add(first.val);

                if (first.left != null) {
                    map.put(first.left, position - 1);
                    queue.addLast(first.left);
                }

                if (first.right != null) {
                    map.put(first.right, position + 1);
                    queue.addLast(first.right);
                }
            }
        }

        return new ArrayList<>(listMap.values());
    }

}
