package com.zzzj.leet;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2024-06-17 16:52
 */
public class Leet2289 {

    public static void main(String[] args) {

        int N = 1000;

        for (int i = 0; i < 10000; i++) {

            int[] arr = ArrayUtil.generateArray(N, 1, N);

            if (!check(arr)) {
                return;
            }

        }

        System.out.println("Ok");

    }

    public static boolean check(
            int[] nums
    ) {

        int r = totalSteps(nums);

        int rr = right(nums);

        if (r != rr) {
            System.out.println("r = " + r);
            System.out.println("rr = " + rr);
            System.out.println("cost = " + Arrays.toString(cost));
            System.out.println("cost = " + Arrays.toString(c2));
            return false;
        }

        return true;
    }

    static int[] cost;
    static int[] c2;

    // [10,1,2,3,4,5,6,1,2,3]
    public static int totalSteps(int[] nums) {
        int N = nums.length;

        LinkedList<Integer> stack = new LinkedList<>();

        int[] cost = new int[N];

        for (int i = 0; i < N; i++) {

            while (!stack.isEmpty() && nums[stack.peekLast()] <= nums[i]) {
                cost[i] = Math.max(
                        cost[i],
                        cost[stack.removeLast()] + 1
                );
            }

            if (stack.isEmpty())
                cost[i] = 0;
            else if (cost[i] == 0)
                cost[i] = 1;

            stack.add(i);
        }

        return Arrays.stream(cost).max().orElse(0);
    }

    public static int right(int[] nums) {

        int N = nums.length;

        c2 = new int[N];

        boolean any = false;

        int times = 1;

        do {
            any = false;
            int prevNum = -1;
            for (int i = 0; i < N; i++) {
                if (c2[i] != 0)
                    continue;
                if (nums[i] < prevNum) {
                    c2[i] = times;
                    any = true;
                }
                prevNum = nums[i];
            }
            times++;
        } while (any);

        return Arrays.stream(c2).max().orElse(0);
    }

}
