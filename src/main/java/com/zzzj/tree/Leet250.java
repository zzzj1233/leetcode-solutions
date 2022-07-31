package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-04-07 12:20
 */
public class Leet250 {

    public static void main(String[] args) {
        System.out.println(countUnivalSubtrees(TreeNode.buildTree("[5,5,5,5,5,null,5]")));
    }

    public static int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root).count;
    }

    private static class NodeInfo {
        boolean allSame;
        int count;

        public NodeInfo(boolean allSame, int count) {
            this.allSame = allSame;
            this.count = count;
        }

        public NodeInfo() {
        }
    }

    public static NodeInfo dfs(TreeNode root) {
        if (root == null) {
            return null;
        }

        NodeInfo leftInfo = dfs(root.left);

        NodeInfo rightInfo = dfs(root.right);

        NodeInfo nodeInfo = new NodeInfo();

        if (leftInfo == null && rightInfo == null) {
            nodeInfo.allSame = true;
            nodeInfo.count = 1;
        } else if (leftInfo != null && rightInfo != null) {
            nodeInfo.allSame = leftInfo.allSame && rightInfo.allSame && root.val == root.left.val && root.left.val == root.right.val;
            nodeInfo.count = leftInfo.count + rightInfo.count + (nodeInfo.allSame ? 1 : 0);
        } else if (leftInfo != null) {
            nodeInfo.allSame = leftInfo.allSame && root.val == root.left.val;
            nodeInfo.count = nodeInfo.allSame ? leftInfo.count + 1 : leftInfo.count;
        } else {
            nodeInfo.allSame = rightInfo.allSame && root.val == root.right.val;
            nodeInfo.count = nodeInfo.allSame ? rightInfo.count + 1 : rightInfo.count;
        }

        return nodeInfo;
    }


}
