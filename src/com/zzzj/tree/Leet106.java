package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-11-16 16:17
 */
public class Leet106 {

    public static void main(String[] args) {
        System.out.println(buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3}).serialize());
    }

    // 中序遍历 inorder   = [9,3,15,20,7]
    // 后序遍历 postorder = [9,15,7,20,3]
    //     3
    //    / \
    //   9   20
    //       / \
    //      15  7
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        // 后续遍历的最后一个节点一定是root
        if (inorder == null || inorder.length == 0) {
            return null;
        }
        return build(inorder, postorder);
    }

    private static TreeNode build(int[] inorder, int[] postorder) {
        if (inorder.length == 0) {
            return null;
        }

        int rootVal = postorder[postorder.length - 1];

        TreeNode root = new TreeNode(rootVal);

        int rootIndex = 0;

        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }

        int[] leftInOrder = Arrays.copyOfRange(inorder, 0, rootIndex);

        int[] leftPostOrder = Arrays.copyOfRange(postorder, 0, rootIndex);

        int[] rightInOrder = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);

        int[] rightPostOrder = Arrays.copyOfRange(postorder, rootIndex , postorder.length - 1);

        root.left = build(leftInOrder, leftPostOrder);

        root.right = build(rightInOrder, rightPostOrder);

        return root;
    }

}
