package com.zzzj.backtracking;

/**
 * @author zzzj
 * @create 2022-03-29 16:38
 */
public class Leet2447 {

    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }

    public static int ans;

    public static int findTargetSumWays(int[] nums, int target) {
        ans = 0;

        dfs(nums, target, 0);

        return ans;
    }

    public static void dfs(int[] nums, int target, int index) {
        if (index == nums.length) {
            ans += target == 0 ? 1 : 0;
            return;
        }

        dfs(nums, target + nums[index], index + 1);
        dfs(nums, target - nums[index], index + 1);
    }

}
