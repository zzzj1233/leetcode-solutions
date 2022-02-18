package com.zzzj.leet;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author zzzj
 * @create 2021-10-27 16:37
 */
public class Leet94 {

    public static void main(String[] args) {
        System.out.println(inorderTraversal(TreeNode.buildTree("[1,2,3,4,5]")));
    }


    // 二叉树的中序遍历
    public static List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;

        List<Integer> answer = new LinkedList<>();

        while (!stack.isEmpty() || cur != null) {
            if (cur == null) {
                cur = stack.pop();
                answer.add(cur.val);
                cur = cur.right;
            } else {
                stack.add(cur);
                cur = cur.left;
            }
        }

        return answer;
    }

}
