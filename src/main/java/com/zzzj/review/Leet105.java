package com.zzzj.review;

import com.zzzj.leet.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-03-16 17:26
 */
public class Leet105 {

    public static void main(String[] args) {
        System.out.println(buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}).serialize());
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {

        Map<Integer, Integer> inMap = new HashMap<>(inorder.length);

        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }


        return build(preorder, 0, 0, inorder.length - 1, inMap);
    }

    public static TreeNode build(
            int[] pre,
            int index,
            int inStart,
            int inEnd,
            Map<Integer, Integer> indexMap
    ) {

        if (inStart > inEnd) {
            return null;
        }

        int nodeVal = pre[index];

        TreeNode node = new TreeNode(nodeVal);

        if (inStart == inEnd) {
            return node;
        }

        Integer inIndex = indexMap.get(nodeVal);

        int leftCount = inIndex - inStart;

        node.left = build(pre, index + 1, inStart, inIndex - 1, indexMap);

        node.right = build(pre, index + leftCount + 1, inIndex + 1, inEnd, indexMap);

        return node;
    }

}
