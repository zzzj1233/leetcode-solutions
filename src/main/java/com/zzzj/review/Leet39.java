package com.zzzj.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-03-10 18:07
 */
public class Leet39 {

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 5}, 8));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        Arrays.sort(candidates);

        List<List<Integer>> ans = new ArrayList<>();

        backtracking(candidates, target, ans, new LinkedList<>());

        return ans;
    }

    public static void backtracking(int[] candidates,
                                    int target,
                                    List<List<Integer>> ans,
                                    LinkedList<Integer> path
    ) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < candidates.length; i++) {

            int candidate = candidates[i];

            if (!path.isEmpty() && candidate < path.peekLast()) {
                continue;
            }

            if (candidate > target) {
                return;
            }

            path.add(candidate);
            backtracking(candidates, target - candidate, ans, path);
            path.removeLast();
        }

    }

}
