package com.zzzj.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-08-15 16:28
 */
public class Leet2096 {

    public static void main(String[] args) {
        System.out.println(getDirections(TreeNode.buildTree("[2,1]"), 2, 1));
    }

    public static String getDirections(TreeNode root, int startValue, int destValue) {
        Map<Integer, TreeNode> parentMap = new HashMap<>();

        TreeNode commonParent = commonParent(root, startValue, destValue);

        buildParent(root, null, parentMap);

        StringBuilder ans = new StringBuilder();

        if (commonParent.val != startValue) {
            TreeNode parent = parentMap.get(startValue);

            ans.append("U");

            while (parent != commonParent) {
                ans.append("U");
                parent = parentMap.get(parent.val);
            }
        }

        StringBuilder path2 = new StringBuilder();

        if (commonParent.val != destValue) {

            TreeNode parent = parentMap.get(destValue);

            if (isLeft(parent, destValue)) {
                path2.append("L");
            } else {
                path2.append("R");
            }

            while (parent != commonParent) {
                int val = parent.val;
                parent = parentMap.get(val);
                if (isLeft(parent, val)) {
                    path2.append("L");
                } else {
                    path2.append("R");
                }
            }
        }


        return ans.append(path2.reverse()).toString();
    }

    public static boolean isLeft(TreeNode parent, int val) {
        return parent.left != null && parent.left.val == val;
    }

    public static void buildParent(TreeNode root, TreeNode parent, Map<Integer, TreeNode> parentMap) {
        if (root == null) {
            return;
        }
        parentMap.put(root.val, parent);
        buildParent(root.left, root, parentMap);
        buildParent(root.right, root, parentMap);
    }

    public static TreeNode commonParent(TreeNode root, int startValue, int destValue) {
        if (root == null) {
            return null;
        }

        if (root.val == startValue || root.val == destValue) {
            return root;
        }

        TreeNode left = commonParent(root.left, startValue, destValue);
        TreeNode right = commonParent(root.right, startValue, destValue);

        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        return root;
    }

}
