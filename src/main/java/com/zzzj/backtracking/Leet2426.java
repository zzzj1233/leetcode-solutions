package com.zzzj.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-03-29 16:08
 */
public class Leet2426 {

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
        System.out.println(combinationSum(new int[]{2, 3, 5}, 8));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        dfs(ans, candidates, target, 0, new LinkedList<>());

        return ans;
    }

    // 可以重复选择
    public static void dfs(List<List<Integer>> ans, int[] candidates, int target, int index, LinkedList<Integer> path) {

        if (target < 0) {
            return;
        }

        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(ans, candidates, target - candidates[i], i, path);
            path.removeLast();
        }

    }

}
