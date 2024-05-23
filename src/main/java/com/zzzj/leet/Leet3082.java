package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zzzj
 * @create 2024-03-21 17:39
 */
public class Leet3082 {

    public static void main(String[] args) {

        System.out.println(sumOfPower(new int[]{1, 2, 3}, 3));

    }

    public static int sumOfPower(int[] nums, int k) {

        final int MOD = 1000000007;

        int N = nums.length;

        int[] ps = new int[N + 1];

        for (int i = 1; i <= N; i++)
            ps[i] = ps[i - 1] + nums[i - 1];

        long ans = 0;

        Map<Integer, Set<Integer>> cnt = new HashMap<>();

        for (int left = 0; left < N; left++) {

            for (int right = left; right < N; right++) {

                cnt.clear();

                for (int x = left; x <= right; x++) {


                }

            }


        }

        return (int) ans;
    }

}
