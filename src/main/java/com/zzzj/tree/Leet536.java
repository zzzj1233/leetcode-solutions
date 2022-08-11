package com.zzzj.tree;


/**
 * @author zzzj
 * @create 2022-08-05 12:23
 */
public class Leet536 {

    public static void main(String[] args) {
        System.out.println(str2tree("4(2(3)(1))(6(5))").serialize());
    }

    public static TreeNode str2tree(String s) {
        if (s.isEmpty()) {
            return null;
        }
        return dfs(s, 0).node;
    }

    public static NodeInfo dfs(String s, int index) {
        if (index > s.length()) {
            return new NodeInfo(null, index + 1);
        }

        int[] num = getNum(s, index);

        TreeNode node = new TreeNode(num[0]);

        index = num[1];

        while (index < s.length() && s.charAt(index) != ')') {
            char c = s.charAt(index);
            if (c == '(') {
                NodeInfo nodeInfo = dfs(s, index + 1);
                if (node.left == null) {
                    node.left = nodeInfo.node;
                } else {
                    node.right = nodeInfo.node;
                }
                index = nodeInfo.index + 1;
            }
        }
        return new NodeInfo(node, index);
    }

    public static int[] getNum(String s, int index) {
        boolean negative = false;
        if (s.charAt(index) == '-') {
            negative = true;
            index++;
        }
        int num = 0;
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            num = num * 10 + Character.digit(s.charAt(index), 10);
            index++;
        }
        return new int[]{negative ? -num : num, index};
    }

    private static class NodeInfo {
        TreeNode node;
        int index;

        public NodeInfo(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

}
