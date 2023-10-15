package com.zzzj.contest.week364;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Q2 {

    public static void main(String[] args) {

        System.out.println(maximumSumOfHeights(Arrays.asList(1, 12, 19)));

        System.out.println(maximumSumOfHeights(Arrays.asList(5, 3, 4, 1, 1)));

        System.out.println(maximumSumOfHeights(Arrays.asList(6, 5, 3, 9, 2, 7)));

        System.out.println(maximumSumOfHeights(Arrays.asList(3, 2, 5, 5, 2, 3)));

    }

    public static long maximumSumOfHeights(List<Integer> maxHeights) {

        int N = maxHeights.size();

        long[] left = new long[N];

        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < N; i++) {

            Integer height = maxHeights.get(i);

            while (!stack.isEmpty() && maxHeights.get(stack.peekLast()) > height) {
                stack.removeLast();
            }

            if (stack.isEmpty())
                left[i] = (long) (i + 1) * height;
            else
                left[i] = left[stack.peekLast()] + (long) (i - stack.peekLast()) * height;

            stack.add(i);
        }

        long[] right = new long[N];

        stack.clear();

        for (int i = N - 1; i >= 0; i--) {

            Integer height = maxHeights.get(i);

            while (!stack.isEmpty() && maxHeights.get(stack.peekLast()) > height) {
                stack.removeLast();
            }

            if (stack.isEmpty())
                right[i] = (long) (N - i) * height;
            else
                right[i] = (long) (stack.peekLast() - i) * height + right[stack.peekLast()];

            stack.add(i);
        }

        long ans = 0;

//        for (int i = 0; i < N; i++) {
//            System.out.printf("left = %d , right = %d , height = %d %n", left[i], right[i], maxHeights.get(i));
//        }

        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, left[i] + right[i] - maxHeights.get(i));
        }

        return ans;
    }

}
