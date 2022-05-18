package com.zzzj.leet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-05-18 11:40
 */
public class Leet429 {

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public static List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }

        // N叉树的层序遍历
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);

        List<List<Integer>> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();

            ArrayList<Integer> list = new ArrayList<>(size);

            for (int i = 0; i < size; i++) {
                Node node = queue.remove();
                list.add(node.val);
                queue.addAll(node.children);
            }

            ans.add(list);
        }

        return ans;
    }

}
