package com.zzzj.leet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-10-20 12:11
 */
public class Leet257 {

    public static void main(String[] args) {
        System.out.println(binaryTreePaths(TreeNode.buildTree("[1,2,3,null,5]")));
    }

    private static List<String> res;

    private static void buildPath(TreeNode root, StringBuilder stringBuilder) {
        stringBuilder.append(root.val);

        if (root.left == null && root.right == null) {
            res.add(stringBuilder.toString());
        }

        if (root.left != null) {
            StringBuilder copy = new StringBuilder(stringBuilder);
            copy.append("->");
            buildPath(root.left, copy);
        }
        if (root.right != null) {
            StringBuilder copy = new StringBuilder(stringBuilder);
            copy.append("->");
            buildPath(root.right, copy);
        }
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        res = new ArrayList<>();

        buildPath(root, new StringBuilder());

        return res;
    }

}
