package com.zzzj.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-08-09 12:25
 */
public class Leet1522 {

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    static int ans = 0;

    public static int diameter(Node root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    public static int dfs(Node root) {
        int max = 0;
        int max2 = 0;

        for (Node child : root.children) {
            int dfs = dfs(child);
            if (dfs >= max) {
                max2 = max;
                max = dfs;
            } else if (dfs > max2) {
                max2 = dfs;
            }
        }

        System.out.printf("root = %d , max = %d , max2 = %d \n", root.val, max, max2);

        ans = Math.max(ans, max + max2);

        return max + 1;
    }


}
