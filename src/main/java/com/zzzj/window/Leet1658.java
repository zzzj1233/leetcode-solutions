package com.zzzj.window;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;


/**
 * @author zzzj
 * @create 2022-02-24 15:45
 */
public class Leet1658 {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            int[] arr = ArrayUtil.generateArray(10000, 0, (int) Math.pow(10, 4));
            int x = Math.abs(LeetUtils.random.nextInt());

            if (minOperations(arr, x) != right(arr, x)) {
                System.out.println("Error");
                System.out.println(minOperations(arr, x));
                System.out.println(right(arr, x));
                return;
            }
        }
    }

    public static int minOperations(int[] nums, int x) {

        long sum = 0;

        int N = nums.length;

        for (int i = 0; i < N; i++) {
            sum += nums[i];
        }

        long target = sum - x;

        if (target < 0) {
            return -1;
        }

        long curSum = 0;

        int L = 0, R = 0;

        int maxLen = -1;

        while (R < nums.length) {
            curSum += nums[R];
            if (curSum > target) {
                while (curSum > target) {
                    curSum -= nums[L];
                    L++;
                }
            }
            if (curSum == target) {
                maxLen = Math.max(R - L + 1, maxLen);
            }
            R++;
        }

        return maxLen == -1 ? -1 : N - maxLen;
    }

    public static int right(int[] nums, int x) {

        int n = nums.length;
        int left = 0, right = 0;
        int maxLength = -1;
        int sum = 0;

        for (int i = 0; i < n; i++) {

            sum += nums[i];
        }
        int count = sum - x;
        if (count < 0) {

            return -1;
        }
        int sumNums = 0;
        while (right < n) {

            sumNums += nums[right];
            while (sumNums > count) {

                sumNums -= nums[left];
                left++;
            }
            if (sumNums == count) {

                maxLength = Math.max(maxLength, right - left + 1);
            }
            right++;
        }
        if (maxLength == -1) return -1;
        else return n - maxLength;
    }


}
