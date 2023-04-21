package com.zzzj.review;

import com.zzzj.leet.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-03-16 17:20
 */
public class Leet102 {

    public static void main(String[] args) {
        System.out.println(levelOrder(TreeNode.buildTree("[3,9,20,null,null,15,7]")));
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {

        if (root == null) {
            return Collections.emptyList();
        }

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        List<List<Integer>> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> list = new ArrayList<>(queue.size());

            for (int i = 0; i < size; i++) {

                TreeNode node = queue.removeFirst();

                list.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }

            }

            ans.add(list);

        }

        return ans;
    }

}
