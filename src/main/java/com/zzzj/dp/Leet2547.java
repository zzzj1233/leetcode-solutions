package com.zzzj.dp;


/**
 * @author zzzj
 * @create 2023-08-15 17:54
 */
public class Leet2547 {

    public static void main(String[] args) {

        System.out.println(minCost(new int[]{1, 2, 1, 2, 1, 3, 3}, 2));

        System.out.println(minCost(new int[]{1, 2, 1, 2, 1}, 2));

        System.out.println(minCost(new int[]{1, 2, 1, 2, 1}, 5));

    }

    public static int minCost(int[] nums, int k) {

        int N = nums.length;

        int[] cnt = new int[1001];

        int[] dp = new int[N];

        dp[0] = k;

        cnt[nums[0]] = 1;

        int sum = 0;

        for (int i = 1; i < N; i++) {

            int old = cnt[nums[i]];

            if (old == 1)
                sum += 2;
            else if (old > 0)
                sum += 1;

            dp[i] = Math.min(
                    dp[i - 1] + k,
                    k + sum
            );

            cnt[nums[i]]++;

            int[] tmp = new int[1001];

            int sub = 0;

            for (int j = 0; j < i; j++) {

                tmp[nums[j]]++;

                int remain = cnt[nums[j]] - tmp[nums[j]];

                if (remain > 1)
                    sub += 1;
                else if (remain == 1)
                    sub += 2;

                dp[i] = Math.min(
                        dp[i],
                        dp[j] + sum - sub + k
                );
            }

        }

        return dp[N - 1];
    }

}
