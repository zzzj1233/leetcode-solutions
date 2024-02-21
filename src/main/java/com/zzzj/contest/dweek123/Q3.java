package com.zzzj.contest.dweek123;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.*;

/**
 * @author zzzj
 * @create 2024-02-04 15:24
 */
public class Q3 {

    public static void main(String[] args) {

        int N = 1000;

        int M = 500;

        for (int i = 0; i < 10000; i++) {

            int k = LeetUtils.random.nextInt(M) + 1;

            int[] arr = ArrayUtil.generateArray(N, -M, M);

            long r = maximumSubarraySum(arr, k);

            long rr = right(arr, k);

            if (r != rr) {
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("k = " + k);
                return;
            }

        }

        System.out.println("Ok");
    }

    public static long maximumSubarraySum(int[] nums, int k) {

        int N = nums.length;

        Map<Integer, Integer> maxIndex = new HashMap<>(N);

        long[] ps = new long[N + 1];

        for (int i = 1; i <= N; i++)
            ps[i] = ps[i - 1] + nums[i - 1];

        long ans = Long.MIN_VALUE;

        long sum = 0;

        for (int i = 0; i < N; i++) {

            int num = nums[i];

            Integer old = maxIndex.putIfAbsent(num, i);

            if (old != null && num >= ps[i + 1] - ps[old]) {
                maxIndex.put(num, i);
            }

            Integer p1 = maxIndex.get(num - k);

            Integer p2 = maxIndex.get(num + k);

            if (p1 != null)
                ans = Math.max(ans, ps[i + 1] - ps[p1]);

            if (p2 != null)
                ans = Math.max(ans, ps[i + 1] - ps[p2]);

        }

        return ans == Long.MIN_VALUE ? 0 : ans;
    }

    public static long right(int[] nums, int k) {

        int N = nums.length;

        long[] ps = new long[N + 1];

        for (int i = 1; i <= N; i++)
            ps[i] = ps[i - 1] + nums[i - 1];

        Map<Integer, List<Integer>> indexes = new HashMap<>();

        long ans = Long.MIN_VALUE;

        for (int i = 0; i < N; i++) {

            indexes.computeIfAbsent(nums[i], integer -> new ArrayList<>()).add(i);

            for (Integer prev : indexes.getOrDefault(nums[i] + k, Collections.emptyList()))
                ans = Math.max(ans, ps[i + 1] - ps[prev]);

            for (Integer prev : indexes.getOrDefault(nums[i] - k, Collections.emptyList()))
                ans = Math.max(ans, ps[i + 1] - ps[prev]);

        }

        return ans == Long.MIN_VALUE ? 0 : ans;
    }

}
