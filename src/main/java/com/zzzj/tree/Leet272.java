package com.zzzj.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-09-01 12:16
 */
public class Leet272 {

    static LinkedList<Integer> ans;

    public static List<Integer> closestKValues(TreeNode root, double target, int k) {
        ans = new LinkedList<>();
        inOrder(root, target, k);
        return ans;
    }

    public static void inOrder(TreeNode root, double target, int k) {
        if (root == null) {
            return;
        }
        inOrder(root.left, target, k);

        if (ans.size() < k) {
            ans.add(root.val);
        } else {
            Integer first = ans.peekFirst();
            if (Math.abs(first - target) > Math.abs(root.val - target)) {
                ans.removeFirst();
                ans.addLast(root.val);
            }
        }

        inOrder(root.right, target, k);
    }


}
