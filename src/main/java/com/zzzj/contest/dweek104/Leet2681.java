package com.zzzj.contest.dweek104;


import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-08-01 17:33
 */
public class Leet2681 {

    public static void main(String[] args) {

        System.out.println(sumOfPower(new int[]{2, 1, 4}));

    }

    static final int MOD = 1000000007;


    public static int sumOfPower(int[] nums) {

        int N = nums.length;

        LinkedList<Integer> stack = new LinkedList<>();

        int[] left = new int[N];

        int[] right = new int[N];

        // 1,2,3,2,4
        for (int i = 0; i < N; i++) {

            Integer last = null;
            left[i] = i;

            while (!stack.isEmpty() && nums[stack.peekLast()] <= nums[i]) {
                last = stack.removeLast();
                right[last] = i - 1;
            }

            if (last != null) {
                left[i] = left[last];
            }

            stack.add(i);
        }

        while (!stack.isEmpty())
            right[stack.removeLast()] = N - 1;

        // 计算作为最大值的贡献
        long max = 0;

        for (int i = 0; i < N; i++) {
            max += (long) nums[i] * nums[i] * Math.pow(2, right[i] - left[i]);
        }

        System.out.println(max);

        return -1;
    }

}
