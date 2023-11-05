package com.zzzj.contest.dweek116;

import java.util.*;

public class Q4 {

    public static void main(String[] args) {

        System.out.println(sumCounts(new int[]{1, 2, 2}));

        System.out.println(right(new int[]{1, 2, 2}));

        System.exit(0);

        System.out.println(sumCounts(new int[]{1, 2, 3, 2, 1, 4}));

        System.out.println(right(new int[]{1, 2, 3, 2, 1, 4}));

//        System.out.println(sum(new int[]{2, 3, 4}));

    }

    public static long sum(int[] nums) {

        int N = nums.length;

        long sum = Arrays.stream(nums).asLongStream().sum();

        return (N * (N + 1)) * (2 * N + 1) / sum;
    }

    public static int right(int[] nums) {

        final int MOD = 1000000007;

        int N = nums.length;

        long ans = 0;

        long prev = 0;

        List<Long> path = new ArrayList<>();

        for (int i = 0; i < N; i++) {

            Set<Integer> cnt = new HashSet<>();

            for (int j = i; j < N; j++) {

                cnt.add(nums[j]);

                ans = (ans + (long) cnt.size() * cnt.size()) % MOD;
            }

            path.add(ans - prev);

            prev = ans;
        }

        System.out.println("right = " + path);

        return (int) ans;
    }

    public static int sumCounts(int[] nums) {

        int N = nums.length;

        Map<Integer, TreeSet<Integer>> indexes = new HashMap<>(N);

        long sum = 0;

        List<Long> path = new ArrayList<>();

        for (int i = 0; i < N; i++) {

            indexes.computeIfAbsent(nums[i], integer -> new TreeSet<>()).add(i);

            sum = (sum << 1) + (long) indexes.size() * indexes.size();

            path.add(sum);
        }

        return (int) sum;
    }

}
