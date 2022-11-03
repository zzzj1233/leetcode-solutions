package com.zzzj.greedy;

import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-11-01 11:23
 */
public class Leet334 {

    public static void main(String[] args) {


        for (int i = 0; i < 10000; i++) {
            int N = 10;

            int[] arr = ArrayUtil.generateArray(N, 0, 10);

            ArrayCopyIterator iterator = new ArrayCopyIterator(arr);

            if (increasingTriplet(iterator.next()) != right(iterator.next())) {
                System.out.println("Error");
                System.out.println(Arrays.toString(iterator.next()));
                System.out.println("MyAns : " + increasingTriplet(iterator.next()));
                System.out.println("Right : " + right(iterator.next()));
                return;
            }

        }

        System.out.println("Ok");
    }


    public static boolean increasingTriplet(int[] nums) {

        int N = nums.length;

        if (N < 3) {
            return false;
        }

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        int i = 1;
        for (; i < N; i++) {
            if (nums[i] > nums[i - 1]) {
                min1 = nums[i - 1];
                min2 = nums[i];
                break;
            }
        }

        for (int j = i + 1; j < N; j++) {
            int num = nums[j];
            if (num > min2) {
                return true;
            }
            // > min1 < min2
            if (num > min1) {
                min2 = num;
            } else { // 小于 min1
                min1 = num;
            }
        }

        return false;
    }


    public static boolean right(int[] nums) {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > b) return true;
            if (num <= a) a = num;
            else if (num <= b) b = num;
        }
        return false;
    }

}
