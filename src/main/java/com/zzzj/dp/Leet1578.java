package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-08-29 19:02
 */
public class Leet1578 {

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            String color = LeetUtils.randomString0("abcd", 5);

            int[] arr = ArrayUtil.generateArray(color.length(), 1, 5);

            int[] origin = Arrays.copyOfRange(arr, 0, arr.length);
            int[] origin2 = Arrays.copyOfRange(arr, 0, arr.length);
            int[] origin3 = Arrays.copyOfRange(arr, 0, arr.length);

            if (minCost(color, arr) != right(color, origin2)) {
                System.out.println("Error");
                System.out.println("\"" + color + "\"");
                System.out.println(Arrays.toString(origin));
                System.out.println(minCost(color, origin3));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static int minCost(String colors, int[] neededTime) {
        int N = neededTime.length;

        int[] dp = new int[N];

        for (int i = 1; i < N; i++) {
            if (colors.charAt(i) == colors.charAt(i - 1)) {
                // 删除当前
                if (neededTime[i] < neededTime[i - 1]) {
                    dp[i] = neededTime[i] + dp[i - 1];
                    neededTime[i] = neededTime[i - 1];
                } else {
                    dp[i] = neededTime[i - 1] + dp[i - 1];
                }
            } else {
                dp[i] = dp[i - 1];
            }
        }

        return dp[N - 1];
    }

    public static int right(String s, int[] cost) {
        int re = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                if (cost[i - 1] > cost[i]) {
                    re += cost[i];
                    cost[i] = cost[i - 1];
                } else {
                    re += cost[i - 1];
                }
            }
        }
        return re;
    }

}
