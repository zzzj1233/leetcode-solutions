package com.zzzj.hot;

import com.zzzj.leet.TreeNode;

/**
 * @author Zzzj
 * @create 2022-04-09 16:51
 */
public class Leet105 {

    public static void main(String[] args) {
        System.out.println(buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}).serialize());
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public static TreeNode build(int[] preorder, int s1, int s2, int[] inorder, int s3, int s4) {
        if (s3 > s4 || s1 > s2) {
            return null;
        }

        int index = -1;

        TreeNode treeNode = null;

        int newS1 = s1;

        while (index == -1) {

            int curNodeVal = preorder[newS1++];

            treeNode = new TreeNode(curNodeVal);

            // 遍历inorder
            for (int i = s3; i <= s4; i++) {
                if (inorder[i] == curNodeVal) {
                    // left = s3 ~ i - 1
                    // right = i + 1 ~ s4
                    index = i;
                    break;
                }
            }

        }

        treeNode.left = build(preorder, newS1, s2, inorder, s3, index - 1);

        treeNode.right = build(preorder, newS1, s2, inorder, index + 1, s4);

        return treeNode;
    }

}
