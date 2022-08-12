package com.zzzj.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-08-12 17:39
 */
public class Leet894 {

    public static void main(String[] args) {
        System.out.println(allPossibleFBT(7).size());
    }

    public static List<TreeNode> allPossibleFBT(int n) {
        return dfs(n);
    }

    public static List<TreeNode> dfs(int n) {
        // 每个节点要么没有子节点.要么有两个子节点
        if (n % 2 == 0) {
            return Collections.emptyList();
        }

        if (n == 1) {
            return Collections.singletonList(new TreeNode());
        }

        List<TreeNode> list = new ArrayList<>();

        for (int i = 1; i < n; i += 2) {
            // 不要左右节点
            List<TreeNode> ways1 = dfs(i);

            // 要左右节点
            List<TreeNode> ways2 = dfs(n - i - 1);

            for (TreeNode treeNode : ways1) {

                for (TreeNode node : ways2) {

                    TreeNode newNode = new TreeNode();
                    newNode.left = treeNode;
                    newNode.right = node;

                    list.add(newNode);
                }

            }

        }

        return list;
    }

}
