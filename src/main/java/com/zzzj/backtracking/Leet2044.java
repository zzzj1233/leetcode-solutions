package com.zzzj.backtracking;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-08-01 14:54
 */
public class Leet2044 {

    public static void main(String[] args) {
//        System.out.println(countMaxOrSubsets(new int[]{2, 2, 2, 2}));

        for (int i = 0; i < 1000; i++) {
            final int[] arr = ArrayUtil.generateArray(5, 0, 1000);
            if (countMaxOrSubsets(arr) != right(arr)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(countMaxOrSubsets(arr));
                System.out.println(right(arr));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int countMaxOrSubsets(int[] nums) {
        int max = 0;

        for (int num : nums) {
            max |= num;
        }

        return dfs(nums, max, 0, 0);
    }

    public static int dfs(int[] nums, int max, int index, int cur) {
        if (cur > max) {
            return 0;
        }

        if (index >= nums.length) {
            return 0;
        }

        int ret = 0;

        if ((cur | nums[index]) == max) {
            ret = 1;
        }

        // 不要当前的
        ret += dfs(nums, max, index + 1, cur);
        // 要
        ret += dfs(nums, max, index + 1, cur | nums[index]);

        return ret;
    }

    public static int right(int[] nums) {
        // 尝试dfs暴力
        // 1.首先对nums所有元素或得到一个最大值
        int max = 0;
        for (int num : nums) {
            max |= num;
        }
        // 2.dfs所有情况, 可选或可不选
        return rightDfs(0, nums, 0, max);
    }

    private static int rightDfs(int curIndex, int[] nums, int curValue, int max) {
        if (curIndex == nums.length) {
            return curValue == max ? 1 : 0;
        }
        return rightDfs(curIndex + 1, nums, curValue | nums[curIndex], max) + rightDfs(curIndex + 1, nums, curValue, max);
    }

}
