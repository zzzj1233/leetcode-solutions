package com.zzzj.tree;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zzzj
 * @create 2022-08-18 10:30
 */
public class Leet889 {

    public static void main(String[] args) {
        // [2,1,3]
        // [3,1,2]
        TreeNode node = constructFromPrePost(
                new int[]{1, 2, 4, 5, 3, 6, 7},
                new int[]{4, 5, 2, 6, 7, 3, 1}
        );


        //     2
        //   1
        // 3
        System.out.println(node.serialize());
    }

    public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {

        Map<Integer, Integer> postOrderIndex = IntStream.range(0, postorder.length)
                .boxed()
                .collect(Collectors.toMap(index -> postorder[index], Function.identity()));

        int N = postorder.length;

        return dfs(preorder, postorder, 0, 0, N - 1, postOrderIndex);
    }

    public static TreeNode dfs(int[] preorder,
                               int[] postorder,
                               int index,
                               int postLeft,
                               int postRight,
                               Map<Integer, Integer> postOrderIndex) {

        if (index >= preorder.length) {
            return null;
        }

        int value = preorder[index];

        TreeNode node = new TreeNode(value);

        if (postRight < postLeft) {
            return node;
        }

        int leftIndex = index + 1;

        if (leftIndex >= preorder.length) {
            return node;
        }

        Integer leftPostIndex = postOrderIndex.get(preorder[leftIndex]);

        node.left = dfs(preorder, postorder, leftIndex, postLeft, leftPostIndex - 1, postOrderIndex);

        int leftLength = leftPostIndex - postLeft + 1;

        node.right = dfs(preorder, postorder, leftIndex + leftLength, postLeft + leftLength, postRight, postOrderIndex);

        return node;
    }

}
