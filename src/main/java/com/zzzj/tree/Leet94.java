package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author zzzj
 * @create 2021-11-22 09:46
 */
public class Leet94 {

    public static void main(String[] args) {
        System.out.println(inorderTraversal(TreeNode.buildTree("[1,null,2,3]")));
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;

        List<Integer> ans = new LinkedList<>();

        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.add(cur);
                cur = cur.left;
            } else {
                TreeNode pop = stack.pop();
                ans.add(pop.val);
                cur = pop.right;
            }
        }


        return ans;
    }

}
