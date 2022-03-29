package com.zzzj.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-03-29 11:21
 */
public class Leet2429 {

    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[]{1, 1, 2}));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();

        dfs(ans, nums, new LinkedList<>(), new boolean[nums.length]);

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
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            dfs(ans, nums, path, used);
            used[i] = false;
            path.removeLast();
        }
    }

}
