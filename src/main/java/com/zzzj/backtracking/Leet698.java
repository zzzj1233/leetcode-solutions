package com.zzzj.backtracking;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-03-03 23:17
 */
public class Leet698 {

    public static void main(String[] args) {

        System.out.println(canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));

        System.exit(0);

        for (int i = 0; i < 1000; i++) {

            int[] nums = ArrayUtil.generateArray(5, 1, 4);

            int k = LeetUtils.random.nextInt(16) + 1;

            int[] arr2 = Arrays.copyOfRange(nums, 0, nums.length);

            if (canPartitionKSubsets(arr2, k) != right(nums, k)) {
                System.out.println(Arrays.toString(arr2));
                System.out.println(k);
                System.out.println(canPartitionKSubsets(arr2, k));
                return;
            }
        }
    }

    public static boolean ans;

    // 把数组分割成k个非空子集，其总和都相等

    // 数组长度最多16

    // k <= 数组长度
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int N = nums.length;

        int[] preSum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int end = N - k + 1;

        for (int i = 0; i < end; i++) {
            if (dfs(nums, i + 1, k - 1, preSum[i + 1], preSum)) {
                return true;
            }
        }

        return false;
    }

    public static boolean dfs(int[] nums, int index, int k, int sum, int[] preSum) {
        int N = nums.length;

        if (k == 1) {
            // index ~ N
            return preSum[N] - preSum[index] == sum;
        }

        int end = N - index - k + 1;

        for (int i = 0; i < end; i++) {
            if (preSum[index + i + 1] - preSum[index] == sum) {
                if (dfs(nums, index + i, k - 1, sum, preSum)) {
                    return true;
                }
            }
        }

        return false;
    }


    public static boolean right(int[] nums, int k) {
        if (k == 1) {
            return true;
        }

        int len = nums.length;
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        if (nums[len - 1] > target) {
            return false;
        }

        int size = 1 << len;
        boolean[] dp = new boolean[size];
        dp[0] = true;
        int[] currentSum = new int[size];
        for (int i = 0; i < size; i++) {
            // 总是基于 dp[i] = true 的前提下进行状态转移
            if (!dp[i]) {
                continue;
            }

            // 基于当前状态，添加一个数以后
            for (int j = 0; j < len; j++) {
                if ((i & (1 << j)) != 0) {
                    continue;
                }
                int next = i | (1 << j);
                if (dp[next]) {
                    continue;
                }
                if ((currentSum[i] % target) + nums[j] <= target) {
                    currentSum[next] = currentSum[i] + nums[j];
                    dp[next] = true;
                } else {
                    // 由于数组已经排好序，如果 (currentSum[i] % target) + nums[j] > target，剩下的数就没有必要枚举
                    break;
                }
            }
        }
        return dp[size - 1];
    }

}
