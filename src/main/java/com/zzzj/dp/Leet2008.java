package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-08-29 17:06
 */
public class Leet2008 {

    public static void main(String[] args) {

//        1 3 2 -> 4
//        1 4 3 -> 6
//        2 3 2 -> 3
//        2 4 1 -> 3
//        3 4 2 -> 3
        System.out.println(maxTaxiEarnings(4, LeetUtils.convertInts("[[3, 4, 2], [2, 3, 2], [2, 4, 1], [1, 3, 2], [1, 4, 3]]")));

//        System.exit(0);

        int ok = 0;

        for (int i = 0; i < 10000; i++) {
            int n = LeetUtils.random.nextInt(200) + 2;
            int len = LeetUtils.random.nextInt(50) + 1;
            int[][] ints = LeetUtils.randomMatrix(len, 3, 1, n);

            for (int[] item : ints) {
                if (item[1] < item[0]) {
                    int temp = item[0];
                    item[0] = item[1];
                    item[1] = temp;
                } else if (item[1] == item[0]) {
                    item[1]++;
                }
            }

            int[][] copy = new int[len][];

            for (int j = 0; j < len; j++) {
                copy[j] = Arrays.copyOfRange(ints[j], 0, 3);
            }

            int[][] copy2 = new int[len][];

            for (int j = 0; j < len; j++) {
                copy2[j] = Arrays.copyOfRange(ints[j], 0, 3);
            }

            int[][] copy3 = new int[len][];

            for (int j = 0; j < len; j++) {
                copy3[j] = Arrays.copyOfRange(ints[j], 0, 3);
            }

            try {
                if (maxTaxiEarnings(n, ints) != right(n, copy)) {
                    System.out.println("Error");
                    System.out.println(n);
                    System.out.println(Arrays.deepToString(copy2));
                    System.out.println(maxTaxiEarnings(n, copy3));
                    return;
                }
            } catch (Exception e) {
                continue;
            }
            ok++;
        }
        System.out.println("Ok ---> " + ok);
    }

    public static long maxTaxiEarnings(int n, int[][] rides) {
        int len = rides.length;

        Arrays.sort(rides, Comparator.comparingInt(o -> o[1]));

        long[] dp = new long[len];

        dp[0] = rides[0][2] + (rides[0][1] - rides[0][0]);

        for (int i = 1; i < len; i++) {
            int[] ride = rides[i];
            int start = ride[0];
            int end = ride[1];
            long profit = ride[2] + (end - start);

            int preIndex = binarySearch(rides, start);

            if (preIndex == -1) {
                dp[i] = Math.max(profit, dp[i - 1]);
            } else {
                dp[i] = Math.max(profit + dp[preIndex], dp[i - 1]);
            }
        }

        return dp[len - 1];
    }

    // end <= start的元素索引
    public static int binarySearch(int[][] rides, int start) {
        int l = 0;
        int r = rides.length - 1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (rides[mid][1] <= start) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return r;
    }

    public static long right(int n, int[][] rides) {
        // dp[i] 表示第 i 个位置能赚的最大利润
        long[] dp = new long[n + 1];
        List<int[]>[] hash = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            hash[i] = new ArrayList<>();
        }
        // 预处理每个乘客的终点和利润
        for (int[] p : rides) {
            int st = p[0], ed = p[1], tip = p[2];
            hash[ed].add(new int[]{st, ed - st + tip});
        }

        // 对每一个停车点，都可以选择载客或者不载
        // 不载客则 dp[ed] = dp[ed - 1]
        // 载客则选 dp[st] -> dp[ed] 能赚最多的
        for (int ed = 1; ed <= n; ed++) {
            dp[ed] = dp[ed - 1];
            for (int[] p : hash[ed]) {
                int st = p[0], money = p[1];
                dp[ed] = Math.max(dp[ed], dp[st] + money);
            }
        }

        return dp[n];
    }

}
