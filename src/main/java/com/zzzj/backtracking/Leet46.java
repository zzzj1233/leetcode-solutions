package com.zzzj.backtracking;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-01-25 19:04
 */
public class Leet46 {

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
        System.out.println(permute(new int[]{0, 1}));
        System.out.println(permute(new int[]{1}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        process(ans, nums, new LinkedList<>(), new HashSet<>());

        return ans;
    }

    public static void process(List<List<Integer>> ans, int[] nums, LinkedList<Integer> path, Set<Integer> prev) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            if (prev.contains(num)) {
                continue;
            }

            prev.add(num);
            path.add(num);

            process(ans, nums, path, prev);

            path.removeLast();
            prev.remove(num);
        }
    }

}
