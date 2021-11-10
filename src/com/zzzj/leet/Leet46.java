package com.zzzj.leet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zzzj
 * @create 2021-10-18 18:06
 */
public class Leet46 {

    public static void main(String[] args) {
        List<List<Integer>> res = permute(new int[]{1, 1, 2});

        System.out.println(res);
    }

    private static List<List<Integer>> res;

    private static void dfs(List<Integer> list, boolean[] used, Set<String> path, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                list.add(nums[i]);
                System.out.println(i + " - " + nums[i]);
                dfs(list, used, path, nums);
                list.remove((Object) nums[i]);
                used[i] = false;
            }
        }

    }

    /**
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    public static List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();

        if (nums == null) {
            return res;
        }

        boolean[] used = new boolean[nums.length];

        dfs(new ArrayList<>(nums.length), used, new HashSet(nums.length), nums);

        return res;
    }

}
