package com.zzzj.contest.week419;

import com.zzzj.leet.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zzzj
 * @create 2024-10-14 23:12
 */
public class Leet3319 {

    public static void main(String[] args) {

        System.out.println(kthLargestPerfectSubtree(TreeNode.buildTree("[1,2,3,4,5,6,7]"), 1));

    }

    public static List<Integer> perfectNodes = new ArrayList<>();

    public static int kthLargestPerfectSubtree(TreeNode root, int k) {
        perfectNodes.clear();
        dfs(root);
        Collections.sort(perfectNodes);
        Collections.reverse(perfectNodes);
        System.out.println(perfectNodes);
        if (perfectNodes.size() < k)
            return -1;
        return perfectNodes.get(k - 1);
    }

    public static int[] dfs(TreeNode root) {

        if (root == null)
            return new int[]{1, 0};

        int[] l = dfs(root.left);

        int[] r = dfs(root.right);

        if (l[0] == 1 && r[0] == 1 && l[1] == r[1]) {
            perfectNodes.add(l[1] + r[1] + 1);
            return new int[]{1, l[1] + r[1] + 1};
        }

        return new int[]{0, l[1] + r[1] + 1};
    }
}
