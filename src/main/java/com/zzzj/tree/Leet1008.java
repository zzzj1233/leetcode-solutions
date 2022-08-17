package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-15 15:44
 */
public class Leet1008 {

    public static void main(String[] args) {
        System.out.println(nextGreatThan(new int[]{3, 2, 1, 0, 9, 4, 5}, 0, 4));
    }

    public static TreeNode bstFromPreorder(int[] preorder) {
        return dfs(preorder, 0, preorder.length - 1);
    }

    public static TreeNode dfs(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[left]);

        if (left == right) {
            return root;
        }

        int greatThan = nextGreatThan(preorder, left, right);

        root.left = dfs(preorder, left + 1, greatThan - 1);
        root.right = dfs(preorder, greatThan, right);

        return root;
    }

    public static int nextGreatThan(int[] arr, int start, int end) {
        int left = start;
        int right = end;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[start]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }


}
