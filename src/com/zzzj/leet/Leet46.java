package com.zzzj.leet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-10-18 18:06
 */
public class Leet46 {

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 1, 2}));
    }

    private static List<List<Integer>> res;

    private static void dfs(List<Integer> list, boolean[] used, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                list.add(nums[i]);
                dfs(list, used, nums);
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

        dfs(new ArrayList<>(nums.length), used, nums);

        return res;
    }

}
