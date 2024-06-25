package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayCopyIterator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author zzzj
 * @create 2023-01-21 18:39
 */
public class Leet1673 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            int M = 1000;

            int[] arr = new int[M];

            for (int j = 0; j < M; j++) {
                arr[j] = LeetUtils.random.nextInt(M);
            }

            ArrayCopyIterator iterator = ArrayCopyIterator.fromArray(arr);

            int K = LeetUtils.random.nextInt(M) + 1;

            int[] res2 = right(iterator.next(), K);
            int[] res1 = mostCompetitive(iterator.next(), K);

            if (!Arrays.equals(res1, res2)) {
                System.out.println("Error");
                return;
            }
        }

        System.out.println("Ok");
    }

    public static int[] mostCompetitive(int[] nums, int k) {

        int N = nums.length;

        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < N; i++) {

            while (!stack.isEmpty() && nums[stack.peekLast()] > nums[i] && (N - i + (stack.size() - 1)) >= k)
                stack.removeLast();

            stack.add(i);
        }

        int[] ans = new int[k];

        for (int i = 0; i < k; i++)
            ans[i] = nums[stack.removeFirst()];

        return ans;
    }

    public static int[] right(int[] nums, int k) {
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && stack.peek() > nums[i] && (nums.length - i + stack.size() - 1) >= k) {
                stack.pop();
            }
            stack.push(nums[i]);
        }
        int[] res = new int[k];
        while (!stack.isEmpty()) {
            if (stack.size() <= k) {
                res[--k] = stack.pop();
            } else {
                stack.pop();
            }
        }
        return res;
    }

}
