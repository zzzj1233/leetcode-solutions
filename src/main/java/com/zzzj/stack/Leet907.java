package com.zzzj.stack;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-08-14 14:46
 */
public class Leet907 {

    public static void main(String[] args) {

        System.out.println(sumSubarrayMins(new int[]{3, 1, 2, 4}));

        // System.out.println(sumSubarrayMins(new int[]{3, 1, 2, 4, 2, 1, 2, 3}));

    }

    static final int MOD = 1000000007;

    public static int sumSubarrayMins(int[] arr) {

        int N = arr.length;

        // 左闭右闭区间

        // left[i] = 当前元素作为"最小值"可以延伸到的最左边
        // right[i] = 当前元素作为"最小值"可以延伸到的最右边
        LinkedList<Integer> stack = new LinkedList<>();

        int[] left = new int[N];

        int[] right = new int[N];

        for (int i = 0; i < N; i++) {

            left[i] = i;

            Integer last = null;

            while (!stack.isEmpty() && arr[i] <= arr[stack.peekLast()]) {
                last = stack.removeLast();
                right[last] = i - 1;
            }

            if (last != null)
                left[i] = left[last];

            stack.add(i);
        }

        while (!stack.isEmpty())
            right[stack.removeLast()] = N - 1;

        long ans = 0;

        for (int i = 0; i < N; i++) {
            ans += (long) (i - left[i] + 1) * (right[i] - i + 1) * arr[i];
            ans %= MOD;
        }

        return (int) ans % MOD;
    }

}
