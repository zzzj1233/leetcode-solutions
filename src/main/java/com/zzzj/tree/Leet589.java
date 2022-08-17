package com.zzzj.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-08-17 22:37
 */
public class Leet589 {

    class Node {
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


    public static List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans);
        return ans;
    }


    public static void dfs(Node root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        ans.add(root.val);
        if (root.children != null) {
            for (Node child : root.children) {
                dfs(child, ans);
            }
        }
    }

    public static List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        dfs2(root, ans);
        return ans;
    }


    public static void dfs2(Node root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        if (root.children != null) {
            for (Node child : root.children) {
                dfs2(child, ans);
            }
        }
        ans.add(root.val);
    }
}
