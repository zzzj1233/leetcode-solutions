package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

import java.util.HashMap;

/**
 * @author zzzj
 * @create 2021-11-16 11:34
 */
public class Leet105 {

    public static void main(String[] args) {
        System.out.println(buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}).serialize());
    }

    /**
     * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * Output: [3,9,20,null,null,15,7]
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return build(preorder, inorder, 0, 0, inorder.length - 1, map);
    }

    private static TreeNode build(int[] preorder, int[] inorder, int index, int left, int right, HashMap<Integer, Integer> map) {
        if (left > right || index > preorder.length || index < 0) {
            return null;
        }

        int rootVal = preorder[index];

        TreeNode root = new TreeNode(rootVal);

        if (left == right) {
            return root;
        }

        // skip

        int findIndex = map.get(rootVal);

        int leftCount = findIndex - left;

        root.left = build(preorder, inorder, index + 1, left, findIndex - 1, map);
        root.right = build(preorder, inorder, index + leftCount + 1, findIndex + 1, right, map);

        return root;
    }

}
