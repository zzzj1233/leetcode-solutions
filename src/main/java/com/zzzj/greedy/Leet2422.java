package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2023-01-03 17:26
 */
public class Leet2422 {

    public static void main(String[] args) {
        System.out.println(minimumOperations(new int[]{4, 3, 2, 1, 2, 3, 1}));
        System.out.println(minimumOperations(new int[]{4, 3, 2, 1, 2, 3, 6979}));
    }

    public static int minimumOperations(int[] nums) {

        int N = nums.length;

        int left = 0;
        int right = N - 1;

        ans = 0;

        dfs(nums, left, right, 0, 0);

        return ans;
    }

    static int ans;

    private static int dfs(int[] nums, int left, int right, int leftSum, int rightSum) {
        if (left >= right) {
            return 0;
        }
        leftSum += nums[left];
        rightSum += nums[right];

        if (leftSum == rightSum) {
            return dfs(nums, left + 1, right - 1, 0, 0);
        }

        if (leftSum < rightSum) {
            ans++;
            return dfs(nums, left + 1, right, leftSum, rightSum - nums[right]);
        } else {
            ans++;
            return dfs(nums, left, right - 1, leftSum - nums[left], rightSum);
        }
    }

}
