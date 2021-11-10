package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-10 18:24
 */
public class Leet1302 {

    private static int answer;

    private static int curLevel;

    public static void main(String[] args) {
        System.out.println(deepestLeavesSum(TreeNode.buildTree("[6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]")));
    }

    public static int deepestLeavesSum(TreeNode root) {
        answer = 0;
        curLevel = 0;
        dfs(root, 1);
        return answer;
    }

    private static void dfs(TreeNode root, int level) {
        if (root.left != null) {
            dfs(root.left, level + 1);
        }

        if (root.right != null) {
            dfs(root.right, level + 1);
        }

        if (level > curLevel) {
            answer = root.val;
            curLevel = level;
        } else if (level == curLevel){
            answer += root.val;
        }
    }

}
