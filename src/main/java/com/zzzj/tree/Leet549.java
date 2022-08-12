package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-11 14:37
 */
public class Leet549 {
    public static void main(String[] args) {
        System.out.println(longestConsecutive(TreeNode.buildTree("[2,1,3]")));
    }

    public static int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }

        NodeInfo nodeInfo = dfs(root);

        return Math.max(nodeInfo.incr, Math.max(nodeInfo.decr, nodeInfo.max));
    }

    private static class NodeInfo {
        public int incr;
        public int decr;
        // 可以不包含当前节点
        public int max;

        public NodeInfo(int incr, int decr, int max) {
            this.incr = incr;
            this.decr = decr;
            this.max = max;
        }

        public NodeInfo() {
            this(1, 1, 1);
        }
    }

    private static NodeInfo dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new NodeInfo();
        }

        int val = root.val;

        NodeInfo info = new NodeInfo();

        if (root.left != null && root.right != null) {
            NodeInfo left = dfs(root.left);
            NodeInfo right = dfs(root.right);

            int leftValue = root.left.val;
            int rightValue = root.right.val;

            if (val == leftValue + 1) {
                info.incr = left.incr + 1;
            } else if (val == leftValue - 1) {
                info.decr = left.decr + 1;
            }

            if (val == rightValue + 1) {
                info.incr = Math.max(info.incr, right.incr + 1);
            } else if (val == rightValue - 1) {
                info.decr = Math.max(info.decr, right.decr + 1);
            }

            info.max = Math.max(info.incr, Math.max(info.decr, Math.max(left.max, right.max)));

            if (val == leftValue + 1 && val == rightValue - 1) {
                info.max = Math.max(info.max, left.incr + right.decr + 1);
            } else if (val == leftValue - 1 && val == rightValue + 1) {
                info.max = Math.max(info.max, left.decr + right.incr + 1);
            }

        } else if (root.left != null) {
            NodeInfo left = dfs(root.left);
            int leftValue = root.left.val;

            if (val == leftValue + 1) {
                info.incr = left.incr + 1;
            } else if (val == leftValue - 1) {
                info.decr = left.decr + 1;
            }

            info.max = Math.max(info.incr, Math.max(info.decr, left.max));
        } else {
            NodeInfo right = dfs(root.right);

            int rightValue = root.right.val;

            if (val == rightValue + 1) {
                info.incr = right.incr + 1;
            } else if (val == rightValue - 1) {
                info.decr = right.decr + 1;
            }

            info.max = Math.max(info.incr, Math.max(info.decr, right.max));
        }

        return info;
    }


}
