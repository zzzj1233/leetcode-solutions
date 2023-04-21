package com.zzzj.review;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-03-15 17:54
 */
public class Leet78 {

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        backtracking(ans, nums, 0, new LinkedList<>(), new boolean[nums.length]);

        return ans;
    }

    public static void backtracking(
            List<List<Integer>> ans,
            int[] nums,
            int index,
            LinkedList<Integer> path,
            boolean[] visited
    ) {

        if (index >= nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < nums.length; i++) {

            if (visited[i]) {
                return;
            }

            int num = nums[i];

            visited[i] = true;
            backtracking(ans, nums, index + 1, path, visited);
            visited[i] = false;

            path.add(num);

            visited[i] = true;
            backtracking(ans, nums, index + 1, path, visited);
            visited[i] = false;

            path.removeLast();
        }

    }

}
