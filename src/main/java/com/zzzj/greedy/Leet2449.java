package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-03-01 18:37
 */
public class Leet2449 {

    public static void main(String[] args) {

        System.out.println(makeSimilar(new int[]{8, 12, 6}, new int[]{2, 14, 10}));

        System.out.println(makeSimilar(new int[]{1, 2, 5}, new int[]{4, 1, 3}));

    }

    public static long makeSimilar(int[] nums, int[] target) {

        return (f(Arrays.stream(nums)
                        .filter(value -> value % 2 == 0)
                        .sorted()
                        .toArray(),
                Arrays.stream(target)
                        .filter(value -> value % 2 == 0)
                        .sorted()
                        .toArray())
                +
                f(Arrays.stream(nums)
                                .filter(value -> value % 2 != 0)
                                .sorted()
                                .toArray(),
                        Arrays.stream(target)
                                .filter(value -> value % 2 != 0)
                                .sorted()
                                .toArray())) / 4;
    }

    public static long f(int[] arr1, int[] arr2) {

        long extra = 0;

        long result = 0;

        int N = arr1.length;

        for (int i = 0; i < N; i++) {
            int o1 = arr1[i];
            int o2 = arr2[i];

            if (o1 == o2) {
                continue;
            }

            int sub = o1 - o2;

            if (sub > 0) {
                extra += sub;
            } else {
                extra -= sub;
            }

        }

        return extra;
    }

    public static long right(int[] nums, int[] target) {
        Arrays.sort(nums);
        Arrays.sort(target);
        long d1 = 0, d2 = 0, d3 = 0, d4 = 0;
        int a = 0, b = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target[i] % 2 == 0) {
                while (nums[a] % 2 == 1) {
                    a++;
                }
                if (nums[a] > target[i]) {
                    d1 += nums[a] - target[i];
                } else {
                    d2 += target[i] - nums[a];
                }
                a++;
            } else {
                while (nums[b] % 2 == 0) {
                    b++;
                }
                if (nums[b] > target[i]) {
                    d3 += nums[b] - target[i];
                } else {
                    d4 += target[i] - nums[b];
                }
                b++;
            }
        }
        return (Math.max(d1, d2) + Math.min(d3, d4)) >> 1;
    }
}
