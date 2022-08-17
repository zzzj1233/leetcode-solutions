package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-17 15:37
 */
public class Leet2472 {

    public static void main(String[] args) {
//        System.out.println(verifyPostorder(new int[]{1, 3, 2, 6, 5}));
//        System.out.println(verifyPostorder(new int[]{1, 6, 3, 2, 5}));
    }

    public static boolean verifyPostorder(int[] postorder) {
        return dfs(postorder, 0, postorder.length - 1);
    }

    public static boolean dfs(int[] postorder, int left, int right) {
        if (left >= right) {
            return true;
        }

        int root = postorder[right];

        boolean gt = true;

        int leftNode = left;
        int rightNode = right - 1;

        for (int i = right - 1; i >= left; i--) {
            if (postorder[i] > root) {
                if (!gt) {
                    return false;
                }
            } else {
                if (gt) {
                    leftNode = i;
                    gt = false;
                }
            }
        }

        return dfs(postorder, left, leftNode) && dfs(postorder, leftNode + 1, rightNode);
    }

}
