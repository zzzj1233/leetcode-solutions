package com.zzzj.review;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-03-10 19:07
 */
public class Leet46 {

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
    }

    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();

        backtracking(ans, new LinkedHashSet<>(), nums);

        return ans;
    }

    public static void backtracking(
            List<List<Integer>> ans,
            LinkedHashSet<Integer> path,
            int[] nums
    ) {

        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            backtracking(ans, path, nums);
            path.remove(nums[i]);
        }

    }

}
