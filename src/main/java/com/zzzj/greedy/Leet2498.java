package com.zzzj.greedy;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-02-27 18:03
 */
public class Leet2498 {

    public static void main(String[] args) {
        System.out.println(maxJump(new int[]{0, 2, 5, 6, 7}));
        System.out.println(maxJump(new int[]{0, 3, 9}));
    }

    // 二分
    public static int maxJump(int[] stones) {

        // 每块石头最多只能用一次
        // 需要来回
        int left = 1;

        int right = Arrays.stream(stones).max().getAsInt() - Arrays.stream(stones).min().getAsInt();

        int ans = 0;

        TreeSet<Integer> set = new TreeSet<>();

        for (int stone : stones) {
            set.add(stone);
        }

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (check(stones, mid, set)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }

        return ans;
    }


    public static boolean check(int[] stones, int expect, TreeSet<Integer> set) {
        int N = stones.length;

        int cur = stones[0];

        int start = stones[0];

        int end = stones[N - 1];

        Set<Integer> visited = new HashSet<>();

        while (cur != end) {
            Integer floor = set.floor(cur + expect);
            if (floor == null || floor == cur) {
                return false;
            }
            cur = floor;
            visited.add(cur);
        }

        while (cur != start) {
            Integer ceiling = set.ceiling(cur - expect);
            if (ceiling == null || ceiling == cur) {
                return false;
            }
            while (visited.contains(ceiling)) {
                ceiling = set.ceiling(ceiling + 1);
                if (ceiling == null || ceiling == cur) {
                    return false;
                }
            }
            cur = ceiling;
        }

        return true;
    }

}
