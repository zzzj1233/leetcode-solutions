package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zzzj
 * @create 2021-11-09 15:33
 */
public class Leet230 {
    private static int answer;

    public static int kthSmallest(TreeNode root, int k) {
        answer = 0;
        dfs(root, new AtomicInteger(0), k);
        return answer;
    }

    public static void dfs(TreeNode root, AtomicInteger i, int k) {
        if (root.left != null) {
            dfs(root.left, i, k);
            i.getAndIncrement();
        } else {
            // 开始了
            i.getAndIncrement();
        }

        if (i.get() == k) {
            answer = root.val;
        }

        if (root.right != null) {
            dfs(root.right, i, k);
        }
    }

}
