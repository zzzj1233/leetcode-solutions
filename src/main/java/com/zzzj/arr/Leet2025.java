package com.zzzj.arr;

import java.util.stream.IntStream;

public class Leet2025 {

    public static void main(String[] args) {


        System.out.println(waysToPartition(new int[]{2, -1, 2}, 3));

//        System.out.println(waysToPartition(new int[]{0, 0, 0}, 1));
////
//        System.out.println(waysToPartition(new int[]{22, 4, -25, -20, -15, 15, -16, 7, 19, -10, 0, -13, -14}, -33));
////
//        System.out.println(waysToPartition(new int[]{1, 2, 3}, 1));

    }

    public static int waysToPartition(int[] nums, int k) {

        int N = nums.length;

        int[] leftSubRight = new int[N];

        int[] rightSubLeft = new int[N];

        int[] preSum = new int[N + 1];

        for (int i = 1; i <= N; i++) preSum[i] = preSum[i - 1] + nums[i - 1];

        int ans = 0;

        for (int i = 1; i < N; i++) {

            int rs = preSum[N] - preSum[i];

            int ls = preSum[i];

            if (rs == ls) {
                ans++;
            } else {
                rightSubLeft[i] = ls - rs;
                leftSubRight[i] = rs - ls;
            }

        }

        int[] leftSorted = IntStream.range(0, N)
                .boxed()
                .sorted((o1, o2) -> {
                    int diff = leftSubRight[o1] - leftSubRight[o2];
                    return diff == 0 ? o1 - o2 : diff;
                }).mapToInt(value -> value)
                .toArray();

        int[] rightSorted = IntStream.range(0, N)
                .boxed()
                .sorted((o1, o2) -> {
                    int diff = rightSubLeft[o1] - rightSubLeft[o2];
                    return diff == 0 ? o1 - o2 : diff;
                }).mapToInt(value -> value)
                .toArray();

        for (int i = 1; i < N; i++) {

            int diff = nums[i] - k;

            ans = Math.max(
                    ans,
                    binarySearch(nums, leftSorted, i, diff, true) + binarySearch(nums, rightSorted, i, diff, false)
            );
        }

        return -1;
    }

    private static int binarySearch(int[] nums, int[] sorted, int index, int value, boolean leftest) {

        int left = 0;
        int right = nums.length - 1;


        return -1;
    }

}
