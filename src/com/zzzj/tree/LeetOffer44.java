package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-11-08 15:34
 */
public class LeetOffer44 {

    public static void main(String[] args) {

    }

    public static List<Integer> largestValues(TreeNode root) {
        if (root == null){
            return Collections.emptyList();
        }

        return bfs(root);
    }

    private static LinkedList<Integer> bfs(TreeNode root) {
        LinkedList<Integer> answer = new LinkedList<>();

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            Integer max = null;
            for (int i = 0; i < size; i++) {
                TreeNode first = queue.removeFirst();

                if (max == null) {
                    max = first.val;
                } else {
                    max = Math.max(max, first.val);
                }

                if (first.left != null){
                    queue.add(first.left);
                }

                if (first.right != null){
                    queue.add(first.right);
                }

            }

            answer.add(max);
        }

        return answer;
    }

}
