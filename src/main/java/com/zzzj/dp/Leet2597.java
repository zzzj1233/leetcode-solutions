package com.zzzj.dp;


/**
 * @author zzzj
 * @create 2023-08-17 12:16
 */
public class Leet2597 {

    public static void main(String[] args) {

        System.out.println(beautifulSubsets(new int[]{2, 4, 6}, 2));

        System.out.println(beautifulSubsets(new int[]{1}, 1));

    }

    public static int beautifulSubsets(int[] nums, int k) {

        int N = nums.length;

        int[] dp = new int[1 << N];

        int[] ex = new int[N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (nums[j] - nums[i] == k || nums[i] - nums[j] == k)
                    ex[i] |= 1 << j;

        int ans = 0;

        for (int stat = 1; stat < dp.length; stat++)
            if (check(stat, k, nums, ex))
                ans++;

        return ans;
    }

    public static boolean check(int stat, int k, int[] nums, int[] ex) {

        for (int i = 0; i < nums.length; i++)
            if ((stat & (1 << i)) != 0 && (ex[i] & stat) != 0)
                return false;

        return true;
    }

}
