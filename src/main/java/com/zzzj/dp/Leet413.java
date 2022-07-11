package com.zzzj.dp;


/**
 * @author zzzj
 * @create 2022-07-07 16:01
 */
public class Leet413 {

    public static void main(String[] args) {
        System.out.println(dfs(new int[]{1, 2, 3, 4}, 3));
        System.out.println(numberOfArithmeticSlices(new int[]{1, 2, 3, 4, 5}));
    }

    public static int numberOfArithmeticSlices(int[] nums) {

        int ans = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            int sub = nums[i + 1] - nums[i];

            for (int j = i + 2; j < nums.length; j++) {
                if (nums[j] - nums[j - 1] == sub) {
                    ans++;
                } else {
                    break;
                }
            }

        }

        return ans;
    }

    public static int dfs(int[] nums, int index) {
        if (index < 2) {
            return 0;
        }

        if (nums[index] - nums[index - 1] == nums[index - 1] - nums[index - 2]) {
            return dfs(nums, index - 1) + 2;
        }

        return dfs(nums, index - 1);
    }


}
