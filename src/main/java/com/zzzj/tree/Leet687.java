package com.zzzj.tree;

import com.zzzj.leet.LeetUtils;
import com.zzzj.leet.TreeNode;

/**
 * @author Zzzj
 * @create 2022-07-30 17:50
 */
public class Leet687 {

    public static void main(String[] args) {
        Solution solution = new Solution();

        for (; ; ) {
            TreeNode tree = LeetUtils.randomTree(100, 2, 1000);
            if (longestUnivaluePath(tree) != solution.longestUnivaluePath(tree)) {
                System.out.println("error");
                break;
            }
        }
    }

    public static int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        NodeInfo nodeInfo = dfs(root);
        return Math.max(nodeInfo.maxPath1, nodeInfo.maxPath2);
    }

    private static class NodeInfo {
        // 以当前值的最大路径
        int maxPath1;
        // 可以不以当前值的最大路径
        int maxPath2;
    }

    public static NodeInfo dfs(TreeNode root) {
        if (root == null) {
            return null;
        }

        NodeInfo leftInfo = dfs(root.left);

        NodeInfo rightInfo = dfs(root.right);

        NodeInfo nodeInfo = new NodeInfo();

        int maxPath1 = 0;

        if (leftInfo != null && rightInfo != null) {
            if (root.val == root.left.val && root.val == root.right.val) {
                nodeInfo.maxPath1 = Math.max(leftInfo.maxPath1, rightInfo.maxPath1) + 1;
                nodeInfo.maxPath2 = Math.max(leftInfo.maxPath2, Math.max(rightInfo.maxPath2, leftInfo.maxPath1 + rightInfo.maxPath1 + 2));
            } else {
                if (root.val == root.left.val) {
                    maxPath1 = leftInfo.maxPath1 + 1;
                }
                if (root.val == root.right.val) {
                    maxPath1 = Math.max(maxPath1, rightInfo.maxPath1) + 1;
                }
                nodeInfo.maxPath1 = maxPath1;
                nodeInfo.maxPath2 = Math.max(leftInfo.maxPath2, Math.max(rightInfo.maxPath2, maxPath1));
            }
        } else if (leftInfo == null && rightInfo == null) {
            nodeInfo.maxPath1 = 0;
            nodeInfo.maxPath2 = 0;
        } else if (leftInfo != null) {  // rightInfo == null
            if (root.val == root.left.val) {
                nodeInfo.maxPath1 = leftInfo.maxPath1 + 1;
            }
            nodeInfo.maxPath2 = Math.max(nodeInfo.maxPath1, leftInfo.maxPath2);
        } else {    // leftInfo == null
            if (root.val == root.right.val) {
                nodeInfo.maxPath1 = rightInfo.maxPath1 + 1;
            }
            nodeInfo.maxPath2 = Math.max(nodeInfo.maxPath1, rightInfo.maxPath2);
        }

        return nodeInfo;
    }


    private static class Solution {
        int ans;

        public int longestUnivaluePath(TreeNode root) {
            ans = 0;
            arrowLength(root);
            return ans;
        }

        public int arrowLength(TreeNode node) {
            if (node == null) return 0;
            int left = arrowLength(node.left);
            int right = arrowLength(node.right);
            int arrowLeft = 0, arrowRight = 0;
            if (node.left != null && node.left.val == node.val) {
                arrowLeft += left + 1;
            }
            if (node.right != null && node.right.val == node.val) {
                arrowRight += right + 1;
            }
            ans = Math.max(ans, arrowLeft + arrowRight);
            return Math.max(arrowLeft, arrowRight);
        }
    }


}
