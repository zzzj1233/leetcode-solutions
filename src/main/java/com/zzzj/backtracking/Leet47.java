package com.zzzj.backtracking;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-02-11 14:23
 */
public class Leet47 {

    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[]{1, 1, 2}));
        System.out.println(permuteUnique(new int[]{1, 2, 3}));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();

        LinkedList<Integer> path = new LinkedList<>();

        boolean[] used = new boolean[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            process(ans, nums, i, path, used);
        }

        return ans;
    }

    public static void process(List<List<Integer>> ans, int[] nums, int cur, LinkedList<Integer> path, boolean[] used) {
        path.add(nums[cur]);

        used[cur] = true;

        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));

            path.removeLast();

            used[cur] = false;

            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) {
                if (!used[i - 1]) {
                    continue;
                }
            }

            if (i == cur || used[i]) {
                continue;
            }

            process(ans, nums, i, path, used);

        }

        path.removeLast();

        used[cur] = false;
    }

}
