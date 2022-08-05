package com.zzzj.backtracking;

import com.zzzj.leet.TreeNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-08-01 14:14
 */
public class Leet95 {

    public static void main(String[] args) {
        System.out.println(generateTrees(3).stream().map(TreeNode::serialize).collect(Collectors.toList()));
    }

    public static List<TreeNode> generateTrees(int n) {
        return dfs(1, n);
    }

    public static List<TreeNode> dfs(int start, int end) {
        List<TreeNode> ret = new LinkedList<>();

        if (start == end) {
            return Collections.singletonList(new TreeNode(start));
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> left = dfs(start, i - 1);
            List<TreeNode> right = dfs(i + 1, end);

            if (left.isEmpty()) {
                for (TreeNode node : right) {
                    final TreeNode newNode = new TreeNode(i);
                    newNode.right = node;
                    ret.add(newNode);
                }
            } else if (right.isEmpty()) {
                for (TreeNode node : left) {
                    final TreeNode newNode = new TreeNode(i);
                    newNode.left = node;
                    ret.add(newNode);
                }
            } else {
                for (TreeNode leftNode : left) {
                    for (TreeNode rightNode : right) {
                        TreeNode newNode = new TreeNode(i);
                        newNode.left = leftNode;
                        newNode.right = rightNode;
                        ret.add(newNode);
                    }
                }
            }
        }

        return ret;
    }

}
