package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-11-12 11:02
 */
public class Leet103 {

    public static void main(String[] args) {
        System.out.println(zigzagLevelOrder(TreeNode.buildTree("[3,9,20,null,null,15,7]")));
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        return bfs(root);
    }

    private static List<List<Integer>> bfs(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        boolean leftToRight = true;

        List<List<Integer>> answer = new LinkedList<>();

        //     3
        //    / \
        //   9  20
        //     /  \
        //    15   7
        while (!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> cell = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node;
                if (leftToRight) {
                    node = queue.removeFirst();
                    if (node.left != null) {
                        queue.addLast(node.left);
                    }
                    if (node.right != null) {
                        queue.addLast(node.right);
                    }
                } else {
                    node = queue.removeLast();
                    if (node.right != null) {
                        queue.addFirst(node.right);
                    }
                    if (node.left != null) {
                        queue.addFirst(node.left);
                    }
                }
                cell.add(node.val);
            }
            answer.add(cell);
            leftToRight = !leftToRight;
        }

        return answer;
    }

}
