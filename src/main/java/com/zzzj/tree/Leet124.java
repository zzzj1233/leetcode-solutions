package com.zzzj.tree;

import com.zzzj.leet.TreeNode;
import com.zzzj.util.Unresolved;

/**
 * @author zzzj
 * @create 2021-11-11 14:34
 */
@Unresolved
public class Leet124 {

    public static void main(String[] args) {
        System.out.println(maxPathSum(TreeNode.buildTree("[5,4,8,11,null,13,4,7,2,null,null,null,1]")));
    }


    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return -1;
    }

    private static void dfs(TreeNode root) {

    }

}
