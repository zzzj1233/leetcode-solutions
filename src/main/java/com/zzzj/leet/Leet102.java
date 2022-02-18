package com.zzzj.leet;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zzzj
 * @create 2021-09-28 18:18
 */
public class Leet102 {

    private Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();

    public static class Pair<K, V> {
        private K key;

        public K getKey() {
            return key;
        }

        private V value;

        public V getValue() {
            return value;
        }

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

        queue.add(new Pair<>(root, 0));

        boolean left = true;

        while (!queue.isEmpty()) {
            final Pair<TreeNode, Integer> pair = queue.remove();

            TreeNode node = pair.getKey();

            if (node.left == null && node.right == null) {
                continue;
            }

            if (result.size() <= pair.getValue()) {
                result.add(new LinkedList<>());
            }

            result.get(pair.getValue()).add(node.val);

            if (left) {
                if (node.left != null) {
                    queue.add(new Pair<>(node.left, pair.getValue() + 1));
                }

                if (node.right != null) {
                    queue.add(new Pair<>(node.right, pair.getValue() + 1));
                }
            } else {
                if (node.right != null) {
                    queue.add(new Pair<>(node.right, pair.getValue() + 1));
                }

                if (node.left != null) {
                    queue.add(new Pair<>(node.left, pair.getValue() + 1));
                }
            }

            left = !left;
        }

        return result;
    }

}
