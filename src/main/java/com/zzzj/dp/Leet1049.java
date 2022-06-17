package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-17 15:43
 */
public class Leet1049 {

    public static void main(String[] args) {
//        System.out.println(lastStoneWeightII(new int[]{2, 7, 4, 1, 8, 1}));
        System.out.println(lastStoneWeightII(new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 14, 23, 37, 61, 98}));
    }

    public static int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();

        int half = sum / 2;

        // 滑动窗口
        Arrays.sort(stones);

        int l = 0;
        int r = 0;
        int curSum = 0;
        int min = Integer.MAX_VALUE;

        OUTER:
        while (r < stones.length) {
            curSum += stones[r];

            while (curSum > half) {
                curSum -= stones[l];
                l++;
                if (l >= r) {
                    break OUTER;
                }
            }

            if (curSum == half) {
                min = 0;
                break;
            }

            min = Math.min(min, Math.min((sum - half) - curSum, sum - curSum));

            r++;
        }

        return -1;
    }

    public static int dfs(int[] stones, int i, int half, int cur) {
        // 要i或者不要i
        if (cur + stones[i] > half) {
            return Integer.MAX_VALUE;
        }
        if (cur + stones[i] == half) {
            return 0;
        }
        // 要i
        int ways1 = dfs(stones, i + 1, half, cur + stones[i]);

        int ways2 = dfs(stones, i + 1, half, cur);

        return Math.min(ways1, Math.min(half - cur, ways2));
    }

}
