package com.zzzj.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-01-25 16:52
 */
public class Leet39 {

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> ans = new ArrayList<>();

        process(ans, candidates, target, 0, new LinkedList<>(), 0);

        return ans;
    }

    public static void process(List<List<Integer>> ans,
                               int[] candidates,
                               int target,
                               int index,
                               LinkedList<Integer> path,
                               int cur) {
        if (cur > target) {
            return;
        }

        if (cur == target) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            path.add(candidates[i]);
            process(ans, candidates, target, i, path, cur + candidates[i]);
            path.removeLast();
        }

    }

}
