package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-24 20:01
 */
public class Leet298 {


    public static void main(String[] args) {
        System.out.println(longestConsecutive(TreeNode.buildTree("[1,null,3,2,4,null,null,null,5]")));
    }


    public static int longestConsecutive(TreeNode root) {
        final Info info = preOrder(root);
        return Math.max(info.val1, info.val2);
    }

    private static class Info {
        // 包含此节点时的最大值
        int val1;
        // 不包含此节点时的最大值
        int val2;

        boolean hasValue;

        public Info(int val1, int val2) {
            this.val1 = val1;
            this.val2 = val2;
        }

        public Info(int val1, int val2, boolean hasValue) {
            this.val1 = val1;
            this.val2 = val2;
            this.hasValue = hasValue;
        }

        public Info() {
        }
    }

    public static Info preOrder(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }

        if (root.left == null && root.right == null) {
            return new Info(1, 1, true);
        }

        Info leftInfo = preOrder(root.left);
        Info rightInfo = preOrder(root.right);

        Info info = new Info();

        int val = root.val;


        info.val1 = 1;

        if (leftInfo.hasValue && val + 1 == root.left.val) {
            info.val1 = leftInfo.val1 + 1;
        }

        if (rightInfo.hasValue && val + 1 == root.right.val) {
            info.val1 = Math.max(info.val1, rightInfo.val1 + 1);
        }

        info.val2 = Math.max(leftInfo.val1, Math.max(leftInfo.val2, Math.max(rightInfo.val1, rightInfo.val2)));

        info.hasValue = true;

        return info;
    }

}
