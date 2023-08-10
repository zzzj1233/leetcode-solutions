package com.zzzj.dp;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-07-04 12:26
 */
public class Leet673 {

    // 1,3,5,4,7 = 2
    // 2,2,2,2,2 = 5

    public static void main(String[] args) {

        System.out.println(findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));

        System.out.println(findNumberOfLIS(new int[]{6, 1, 1, 3, 4}));

//        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            int[] arr = ArrayUtil.generateArray(5, 0, 10);
            if (findNumberOfLIS(arr) != right(arr)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(findNumberOfLIS(arr));
                System.out.println(right(arr));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int findNumberOfLIS(int[] nums) {

        int N = nums.length;

        int[] dp = new int[N];

        int[] cnt = new int[N];

        int max = 0;

        int ans = 0;

        for (int i = 0; i < N; i++) {

            dp[i] = 1;
            cnt[i] = 1;

            for (int j = 0; j < i; j++) {

                if (nums[j] < nums[i]) {

                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];
                    }
                }

            }

            max = Math.max(max, dp[i]);
        }

        for (int i = 0; i < N; i++) {
            if (dp[i] == max)
                ans += cnt[i];
        }

        return ans;
    }


    public static int right(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;

        int[] dp = new int[n];
        int[] count = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);
        int maxLength = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLength) {
                res += count[i];
            }
        }
        return res;
    }

}
