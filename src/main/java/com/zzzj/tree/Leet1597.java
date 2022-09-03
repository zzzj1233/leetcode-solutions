package com.zzzj.tree;


public class Leet1597 {

    static class Node {
        char val;
        Node left;
        Node right;

        Node() {
            this.val = ' ';
        }

        Node(char val) {
            this.val = val;
        }

        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Node node = expTree("2-3/(5*2)+1");

        System.out.println("~");
    }

    private static class NodeInfo {
        Node node;
        int index;

        public NodeInfo(Node node, int index) {
            this.node = node;
            this.index = index;
        }
    }


    public static Node expTree(String s) {
        return dfs(s, 0, '+').node;
    }

    public static NodeInfo dfs(String s, int index, char lastOp) {
        Node left = null;

        int N = s.length();

        while (index < N && s.charAt(index) != ')') {
            char c = s.charAt(index);
            if (c == '(') {
                NodeInfo dfs = dfs(s, index + 1, '+');
                left = dfs.node;
                index = dfs.index;
            } else if (Character.isDigit(c)) {
                left = new Node(c);
                index++;
            } else {
                // 运算符
                Node opNode = new Node(c);
                opNode.left = left;
                NodeInfo dfs = dfs(s, index + 1, c);
                opNode.right = dfs.node;
                index = dfs.index;
                left = opNode;
                if (lastOp == '*' || lastOp == '/') {
                    return new NodeInfo(opNode, index);
                }
            }
        }

        return new NodeInfo(left, index + 1);
    }
}
