package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2021-11-12 12:17
 */
public class Leet437 {

    public static void main(String[] args) {
        System.out.println(pathSum(TreeNode.buildTree("[0,1,1]"), 1));
    }

    private static Map<Integer, Integer> recorder;

    private static int target;

    private static int answer;

    private static int curSum;

    public static int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        recorder = new HashMap<>();
        recorder.put(0, 1);
        target = targetSum;
        answer = 0;
        curSum = 0;
        dfs(root);
        return answer;
    }

    private static void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        int value = root.val;
        curSum += value;

        Integer count = recorder.get(curSum - target);

        if (count != null && count != 0) {
            answer += count;
        }

        count = recorder.get(value - target);

        if (count != null && count != 0) {
            answer += count;
        }

        recorder.put(value, recorder.getOrDefault(value, 0) + 1);

        dfs(root.left);
        dfs(root.right);

        recorder.put(value, recorder.getOrDefault(value, 1) - 1);

        curSum -= value;
    }

}
