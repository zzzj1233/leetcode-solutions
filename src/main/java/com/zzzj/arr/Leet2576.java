package com.zzzj.arr;

import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-03 17:05
 */
public class Leet2576 {

    public static void main(String[] args) {

        System.out.println(maxNumOfMarkedIndices(new int[]{2, 4, 5, 9}));

//        System.exit(0);

        for (int i = 0; i < 10000; i++) {

            int[] arr = ArrayUtil.generateArray(50, 1, 5);

            ArrayCopyIterator it = new ArrayCopyIterator(arr);

            if (maxNumOfMarkedIndices(it.next()) != right(it.next())) {
                System.out.println("maxNumOfMarkedIndices = " + maxNumOfMarkedIndices(it.next()));
                System.out.println("right = " + right(it.next()));
                System.out.println("it.next() = " + Arrays.toString(it.next()));
                break;
            }
        }

    }

    public static int maxNumOfMarkedIndices(int[] nums) {

        Arrays.sort(nums);

        int N = nums.length;

        int mid = (N / 2) - 1;

        int left = mid;

        int right = N - 1;

        int ans = 0;

        for (int i = left; i >= 0 && right > mid; i--) {

            if (nums[left] << 1 <= nums[right]) {
                left--;
                right--;
                ans += 2;
            } else {
                left--;
            }

        }

        return ans;
    }

    public static int right(int[] nums) {

        Arrays.sort(nums);

        int left = 0, right = (nums.length + 1) / 2;
        while (right < nums.length) {
            if (nums[left] * 2 <= nums[right++]) {
                left++;
            }
        }
        return left * 2;

    }
}
