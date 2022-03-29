package com.zzzj.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-03-29 11:31
 */
public class Leet2428 {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        dfs(ans, nums, new LinkedList(), new boolean[nums.length]);

        return ans;
    }

    public static void dfs(List<List<Integer>> ans, int[] nums, LinkedList<Integer> path, boolean[] used) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.addLast(nums[i]);
            dfs(ans, nums, path, used);
            path.removeLast();
            used[i] = false;
        }
    }

}
