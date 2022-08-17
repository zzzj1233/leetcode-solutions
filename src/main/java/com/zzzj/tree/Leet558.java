package com.zzzj.tree;

/**
 * @author Zzzj
 * @create 2022-08-17 22:25
 */
public class Leet558 {

    private static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
        }

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }

    public static Node intersect(Node quadTree1, Node quadTree2) {
        return dfs(quadTree1, quadTree2);
    }

    public static Node dfs(Node quadTree1, Node quadTree2) {
        if (quadTree1.isLeaf && quadTree2.isLeaf) {
            return quadTree1;
        } else {
            // 一个是叶子节点一个不是
            if (quadTree1.isLeaf) {
                if (quadTree1.val) {
                    return quadTree1;
                }
                return quadTree2;
            } else if (quadTree2.isLeaf) {
                if (quadTree2.val) {
                    return quadTree2;
                }
                return quadTree1;
            } else {
                // 都不是叶子节点
                Node node = new Node();
                node.val = true;

                node.topLeft = dfs(quadTree1.topLeft, quadTree2.topLeft);
                node.topRight = dfs(quadTree1.topRight, quadTree2.topRight);
                node.bottomLeft = dfs(quadTree1.bottomLeft, quadTree2.bottomLeft);
                node.bottomRight = dfs(quadTree1.bottomRight, quadTree2.bottomRight);

                if (node.topLeft.val && node.topRight.val && node.bottomLeft.val && node.bottomRight.val) {
                    return new Node(true, true, null, null, null, null);
                }

                return node;
            }
        }
    }


}
