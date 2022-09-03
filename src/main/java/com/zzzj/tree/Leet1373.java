package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-31 15:27
 */
public class Leet1373 {

    public static void main(String[] args) {
        System.out.println(maxSumBST(TreeNode.buildTree("[1,null,10,-5,20]")));
    }

    public static int maxSumBST(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] dfs = dfs(root);
        return Math.max(dfs[0], dfs[1]);
    }

    static final int MIN = 3;
    static final int MAX = 4;

    public static int[] dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            // 0. 包含当前节点
            // 1. 不包含当前节点
            // 2. 是否是搜索二叉树
            // 3. 最小值
            // 4. 最大值
            return new int[]{root.val, root.val, 1, root.val, root.val};
        }

        int[] result = new int[5];

        if (root.left == null) {
            int[] right = dfs(root.right);
            if (root.right.val > root.val && right[2] == 1 && root.val < right[MIN]) {
                result[0] = root.val + result[0] + right[0];
                result[2] = 1;
                result[MAX] = right[MAX];
                result[MIN] = root.val;
            }
            result[1] = Math.max(result[0], right[1]);
        } else if (root.right == null) {
            int[] left = dfs(root.left);
            if (root.val > root.left.val && left[2] == 1 && root.val > left[MAX]) {
                result[0] = root.val + result[0] + left[0];
                result[2] = 1;
                result[MIN] = left[MIN];
                result[MAX] = root.val;
            }
            result[1] = Math.max(result[0], left[1]);
        } else {
            int[] left = dfs(root.left);
            int[] right = dfs(root.right);

            if (root.val > root.left.val && root.val < root.right.val && left[2] == 1 && right[2] == 1
                    && root.val > left[MAX] && root.val < right[MIN]
            ) {
                result[0] = root.val + left[0] + right[0];
                result[2] = 1;
                result[MIN] = left[MIN];
                result[MAX] = right[MAX];
            }
            result[1] = Math.max(result[0], Math.max(left[1], right[1]));
        }

        return result;
    }

}
