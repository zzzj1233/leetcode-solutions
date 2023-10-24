package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-10-18 15:46
 */
public class Leet1793 {

    public static void main(String[] args) {


//        System.exit(0);

        for (int i = 0; i < 10000; i++) {
            int M = 500;

            int[] arr = ArrayUtil.generateArray(M, 1, M);

            int k = LeetUtils.random.nextInt(arr.length);

            int r1 = maximumScore(arr, k);

            int rr = right(arr, k);

            if (r1 != rr) {
                System.out.println("r1 = " + r1);
                System.out.println("rr = " + rr);
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("k = " + k);
                return;
            }

        }

    }

    public static int maximumScore(int[] nums, int k) {

        int N = nums.length;

        int[] left = new int[N];
        int[] right = new int[N];

        LinkedList<Integer> stack = new LinkedList<>();

        Arrays.fill(left, -1);
        Arrays.fill(right, N);

        for (int i = 0; i < N; i++) {

            while (!stack.isEmpty() && nums[stack.peekLast()] >= nums[i]) {
                stack.removeLast();
            }

            if (!stack.isEmpty())
                left[i] = stack.peekLast();

            stack.addLast(i);
        }

        stack.clear();

        for (int i = N - 1; i >= 0; i--) {

            while (!stack.isEmpty() && nums[stack.peekLast()] >= nums[i]) {
                stack.removeLast();
            }

            if (!stack.isEmpty())
                right[i] = stack.peekLast();

            stack.addLast(i);
        }

        int ans = 0;

        for (int i = 0; i < N; i++) {

            if (left[i] < k && right[i] > k) {
                ans = Math.max(
                        ans,
                        nums[i] * (right[i] - left[i] - 1)
                );
            }

        }

        return ans;
    }

    public static int right(int[] nums, int k) {
        int l = k, r = k, n = nums.length, res = 0; //定义左右边界l r，最大可能分数res
        while (true) {
            while (r < n && nums[r] >= nums[k]) r++; //向右寻找以nums[k]为最小值的好子数组
            while (l >= 0 && nums[l] >= nums[k]) l--; //向左寻找以nums[k]为最小值的好子数组
            res = Math.max(res, (r - l - 1) * nums[k]);  //更新最大可能分数
            if (l < 0 && r == n) break; //遍历完数组，直接退出循环
            if (l >= 0 && r < n) nums[k] = Math.max(nums[l], nums[r]); //更新nums[k] 为左右边界中的较大者
            else if (l < 0) nums[k] = nums[r]; //左边遍历完了，更新nums[k]为右边界
            else nums[k] = nums[l]; //右边遍历完了，更新nums[k]为左边界
        }
        return res;
    }

}
