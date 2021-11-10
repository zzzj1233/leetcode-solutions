package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-10-28 17:21
 */
public class Leet740 {

    /**
     * 给你一个整数数组nums，你可以对它进行一些操作。
     * <p>
     * 每次操作中，选择任意一个nums[i]，删除它并获得nums[i]的点数。之后，你必须删除 所有 等于nums[i] - 1 和 nums[i] + 1的元素。
     * <p>
     * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [3,4,2]
     * 输出：6
     * 解释：
     * 删除 4 获得 4 个点数，因此 3 也被删除。
     * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
     * <p>
     * 示例2：
     * <p>
     * 输入：nums = [2,2,3,3,3,4]
     * 输出：9
     * 解释：
     * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
     * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
     * 总共获得 9 个点数。
     */
    public static void main(String[] args) {
//        for (int i = 0; i < 5; i++) {
//            int[] arr = LeetUtils.newArray(50);
//            System.out.println(deleteAndEarn(arr));
//        }
//        System.out.println("--------------- \n");
        System.out.println(deleteAndEarn(new int[]{1, 6, 3, 3, 8, 4, 8, 10, 1, 3}));
    }

    public static int deleteAndEarn(int[] nums) {
        int n = nums.length;

        if (n == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int[] dp = new int[n + 1];
        dp[n] = 0;

        int cur = nums[n - 1];
        int prevSum = 0;
        int curSum = 0;
        int discard = 0;
        int prevNum = 0;

        for (int i = n - 1; i >= 0; i--) {
            // 最后一个元素
            dp[i] = curSum + cur;
            curSum += cur;
            if (i - 1 < 0 || nums[i - 1] != cur) {
                if (prevNum - cur == 1) {
                    // prevSum > dp[i] + discard
                    if (prevSum > dp[i] + discard) {
                        discard = prevSum;
                        dp[i] = prevSum;
                    } else {
                        dp[i] += discard;
                        discard = prevSum;
                    }
                } else {
                    discard = prevSum;
                    dp[i] += prevSum;
                }
                prevSum = dp[i];
                if (i - 1 >= 0) {
                    prevNum = cur;
                    cur = nums[i - 1];
                }
                curSum = 0;
            }
        }


        return dp[0];
    }

}
