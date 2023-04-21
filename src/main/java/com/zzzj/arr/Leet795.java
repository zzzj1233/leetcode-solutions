package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2023-04-12 18:55
 */
// @lajiQuestion
public class Leet795 {

    public static void main(String[] args) {
        System.out.println(numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 2, 3));
        System.out.println(numSubarrayBoundedMax(new int[]{2, 9, 2, 5, 6}, 2, 8));
    }

    // 2
    // 2
    // 5
    // 2 5
    // 6
    // 56
    // 2 5 6

    // (项数 * 项数 + 1) / 2
    public static int numSubarrayBoundedMax(int[] nums, int left, int right) {

        int N = nums.length;

        int ans = 0;

        // 什么窗口是可以被统计的?

        // 1. [left - 1] > max && [left - 1] 不在范围内

        // 2. [right + 1] > max && [right + 1] 不在范围内

        int lastOverflowIndex = 0;

        int lastIndex = 0;

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {

            int num = nums[i];

            max = Math.max(max, num);

            if (max >= left) {

                // 合法范围
                if (max > right || i == N - 1) {
                    ans += ((i - lastOverflowIndex) * (i - lastOverflowIndex + 1)) / 2;
                    lastOverflowIndex = i;
                    max = Integer.MIN_VALUE;
                }

            }

        }

        return ans;
    }

    public static int right(int[] A, int L, int R) {
        // 最大元素满足大于等于L小于等于R的子数组个数 = 最大元素小于等于R的子数组个数 - 最大元素小于L的子数组个数
        return numSubarrayBoundedMax(A, R) - numSubarrayBoundedMax(A, L - 1);
    }

    private static int numSubarrayBoundedMax(int[] A, int Max) {
        int res = 0;
        int numSubarry = 0;
        for (int num : A) {
            if (num <= Max) {
                numSubarry++;
                res += numSubarry;
            } else {
                numSubarry = 0;
            }
        }
        return res;
    }

}
