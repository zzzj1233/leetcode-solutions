package com.zzzj.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-03-29 15:45
 */
public class Leet2427 {

    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
        System.out.println(combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);

        List<List<Integer>> ans = new ArrayList<>();

        dfs(ans, new LinkedList<>(), candidates, target, new boolean[candidates.length], 0);

        return ans;
    }

    public static void dfs(List<List<Integer>> ans, LinkedList<Integer> path, int[] candidates, int target, boolean[] used, int index) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            used[i] = true;
            dfs(ans, path, candidates, target - candidates[i], used, i + 1);
            path.removeLast();
            used[i] = false;
        }
    }

}
