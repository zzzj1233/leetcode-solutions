package com.zzzj.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-08-22 16:45
 */
public class Leet501 {

    public static int[] findMode(TreeNode root) {
        count = maxCount = 0;
        curVal = Integer.MAX_VALUE;
        values = new ArrayList<>();
        inOrder(root);

        int[] ans = new int[values.size()];

        for (int i = 0; i < values.size(); i++) {
            ans[i] = values.get(i);
        }

        return ans;
    }

    static int count;

    static int maxCount;

    static int curVal;

    static List<Integer> values;

    private static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);

        if (root.val == curVal) {
            count++;
        } else {
            count = 1;
            curVal = root.val;
        }

        if (count > maxCount) {
            values.clear();
            values.add(root.val);
            maxCount = count;
        } else if (count == maxCount) {
            values.add(root.val);
        }

        inOrder(root.right);
    }

}
