package com.zzzj.leet;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-10-27 15:49
 */
public class Leet144 {

    public static void main(String[] args) {
        System.out.println(preorderTraversal(TreeNode.buildTree("[1,2,3,4,5]")));
    }

    private static List<Integer> result;

    // 使用栈模拟前序遍历
    public static List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        result = new LinkedList<>();

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode last = stack.removeLast();
            result.add(last.val);
            if (last.right != null) {
                stack.add(last.right);
            }
            if (last.left != null){
                stack.add(last.left);
            }
        }

        return result;
    }


}
