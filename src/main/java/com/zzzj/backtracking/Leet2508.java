package com.zzzj.backtracking;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-03-29 12:06
 */
public class Leet2508 {

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));
        System.out.println(subsets(new int[]{0}));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        dfs(ans, nums, new HashSet<>(nums.length << 2), 0);

        return ans;
    }

    public static void dfs(List<List<Integer>> ans, int[] nums, Set<Integer> used, int cur) {
        if (used.contains(cur)) {
            return;
        }

        LinkedList<Integer> path = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            if ((cur >> i & 1) == 1) {
                path.add(nums[i]);
            }
        }

        used.add(cur);

        for (int i = 0; i < nums.length; i++) {
            if ((cur >> i & 1) == 0) {
                dfs(ans, nums, used, cur | (1 << i));
            }
        }


        ans.add(new ArrayList<>(path));
    }

}
