package com.zzzj.stack;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-08-14 15:46
 */
public class Leet2104 {

    public static void main(String[] args) {

        System.out.println(subArrayRanges(new int[]{1, 2, 3}));

        System.out.println(subArrayRanges(new int[]{1, 3, 3}));

        System.out.println(subArrayRanges(new int[]{4, -2, -3, 4, 1}));

    }

    public static long subArrayRanges(int[] nums) {

        int N = nums.length;

        LinkedList<Integer> stack = new LinkedList<>();

        int[] maxLeft = new int[N];

        int[] maxRight = new int[N];

        for (int i = 0; i < N; i++) {

            maxLeft[i] = i;

            Integer last = null;

            while (!stack.isEmpty() && nums[i] >= nums[stack.peekLast()]) {
                last = stack.removeLast();
                maxRight[last] = i - 1;
            }

            if (last != null)
                maxLeft[i] = maxLeft[last];

            stack.add(i);
        }

        while (!stack.isEmpty())
            maxRight[stack.removeLast()] = N - 1;

        int[] minLeft = new int[N];

        int[] minRight = new int[N];

        for (int i = 0; i < N; i++) {

            minLeft[i] = i;

            Integer last = null;

            while (!stack.isEmpty() && nums[i] <= nums[stack.peekLast()]) {
                last = stack.removeLast();
                minRight[last] = i - 1;
            }

            if (last != null)
                minLeft[i] = minLeft[last];

            stack.add(i);
        }

        while (!stack.isEmpty())
            minRight[stack.removeLast()] = N - 1;

        long ans = 0;

        for (int i = 0; i < N; i++) {
            long add = (maxRight[i] - i + 1) * (i - maxLeft[i] + 1) * (long) nums[i];
            long sub = (minRight[i] - i + 1) * (i - minLeft[i] + 1) * (long) nums[i];
            ans += add - sub;
        }

        return ans;
    }

}
