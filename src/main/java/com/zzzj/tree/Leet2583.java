package com.zzzj.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-04-25 17:54
 */
public class Leet2583 {

    public static void main(String[] args) {
        System.out.println(kthLargestLevelSum(
                TreeNode.buildTree("[5,8,9,2,1,3,7,4,6]"),
                4
        ));

        System.out.println(kthLargestLevelSum(
                TreeNode.buildTree("[1,2,null,3]"),
                3
        ));
    }

    public static long kthLargestLevelSum(TreeNode root, int k) {

        int height = treeHeight(root);

        if (height < k) {
            return -1;
        }

        // 使用大根堆还是小根堆

        int mid = height / 2;

        Heap heap;

        if (k <= mid) {
            heap = new Heap(false, k);
        } else {
            heap = new Heap(true, height - k + 1);
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            long value = 0;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();

                value += node.val;

                if (node.left != null) queue.addLast(node.left);
                if (node.right != null) queue.addLast(node.right);
            }
            heap.add(value);
        }

        return heap.peek();
    }

    static class Heap {

        final boolean minHeap;

        private final PriorityQueue<Long> pq;

        private final int size;

        Heap(boolean minHeap, int size) {

            this.minHeap = minHeap;

            this.size = size;

            Comparator<Long> comparator;

            if (this.minHeap) {
                comparator = Comparator.reverseOrder();
            } else {
                comparator = Comparator.naturalOrder();
            }

            this.pq = new PriorityQueue<Long>(size, comparator);
        }

        public void add(long value) {
            if (pq.size() < size) {
                pq.add(value);
            } else if (minHeap ? value < pq.peek() : value > pq.peek()) {
                pq.remove();
                pq.add(value);
            }
        }

        public Long peek() {
            return pq.peek();
        }
    }

    public static int treeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(treeHeight(root.left), treeHeight(root.right));
    }

}
