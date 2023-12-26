package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-07-19 16:55
 */
public class Leet377 {

    public static void main(String[] args) {

        System.out.println(combinationSum4(new int[]{1, 2, 3}, 4));

        System.out.println(combinationSum4(new int[]{9}, 3));

    }

    public static int combinationSum4(int[] nums, int target) {

        int N = nums.length;

        int[] f = new int[target + 1];

        f[0] = 1;

        for (int v = 0; v <= target; v++) {
            for (int i = 0; i < N; i++) {
                if (nums[i] <= v)
                    f[v] += f[v - nums[i]];
            }
        }

        return f[target];
    }

}
