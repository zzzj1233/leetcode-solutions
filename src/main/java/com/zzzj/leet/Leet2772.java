package com.zzzj.leet;

import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

public class Leet2772 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            int K = 600;

            int[] arr = ArrayUtil.generateArray(K, 0, K);

            int k = LeetUtils.random.nextInt(arr.length);

            while (k <= 1) k = LeetUtils.random.nextInt(arr.length);

            ArrayCopyIterator it = ArrayCopyIterator.fromArray(arr);

            if (checkArray(it.next(), k) != right(it.next(), k)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(it.next()));
                System.out.println(k);
                System.out.println(checkArray(it.next(), k));
                return;
            }
        }

        System.out.println("Right");
    }

    public static boolean checkArray(int[] nums, int k) {

        if (k == 1) return true;

        int N = nums.length;

        int sub = 0;

        int[] diff = new int[N];

        int prev = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {

            sub -= diff[i];

            int num = nums[i] - sub;

            if (N - i < k && num != 0) return false;

            if (num < 0) return false;

            if (num == 0) continue;

            sub += num;

            if (i + k < N) diff[i + k] = num;
        }

        return true;
    }

    public static boolean right(int[] nums, int k) {
        int n = nums.length;
        int[] diff = new int[n + 1];
        diff[0] = nums[0];
        for (int i = 1; i < n; ++i) {
            diff[i] = nums[i] - nums[i - 1];
        }
        for (int i = 0; i < n; ++i) {
            if (diff[i] == 0)
                continue;
            if (diff[i] < 0 || diff[i] > 0 && i + k > n)
                return false;
            int d = diff[i];
            diff[i] -= d;
            diff[i + k] += d;
        }
        return true;
    }

}
