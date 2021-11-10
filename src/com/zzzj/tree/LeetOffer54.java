package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zzzj
 * @create 2021-11-09 14:33
 */
public class LeetOffer54 {

    public static void main(String[] args) {
        System.out.println(kthLargest(TreeNode.buildTree("[2,1]"), 1));
        System.out.println(kthLargest2(TreeNode.buildTree("[2,1]"), 1));
    }

    private static int answer;

    public static int kthLargest2(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        dfs2(root, set);
        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.reverse(list);
        return list.get(k - 1);
    }

    private static void dfs2(TreeNode root, Set<Integer> set) {
        set.add(root.val);
        if (root.left != null) {
            dfs2(root.left, set);
        }
        if (root.right != null) {
            dfs2(root.right, set);
        }
    }

    public static int kthLargest(TreeNode root, int k) {
        answer = 0;
        dfs(root, new AtomicInteger(0), k);
        return answer;
    }


    public static void dfs(TreeNode root, AtomicInteger i, int k) {
        if (root.right != null) {
            dfs(root.right, i, k);
            i.getAndIncrement();
        } else {
            // 开始了
            i.getAndIncrement();
        }

        if (i.get() == k) {
            answer = root.val;
        }

        if (root.left != null) {
            dfs(root.left, i, k);
        }
    }


}
