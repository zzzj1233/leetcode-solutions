package com.zzzj.leet;

import java.awt.font.NumericShaper;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Zzzj
 * @create 2022-07-30 21:03
 */
public class Leet384 {


    private static class Solution {

        private final Random random;

        int[] origin = null;

        int[] nums = null;

        public Solution(int[] nums) {
            this.origin = nums;
            this.nums = Arrays.copyOfRange(origin, 0, origin.length);
            this.random = new Random();
        }

        public int[] reset() {
            this.nums = Arrays.copyOfRange(origin, 0, origin.length);
            return nums;
        }

        public int[] shuffle() {
            int N = nums.length;

            for (int i = 0; i < N; i++) {
                int swapIndex = Math.min(i, random.nextInt(N) - i);
                int temp = nums[swapIndex];
                nums[swapIndex] = nums[i];
                nums[i] = temp;
            }

            return nums;
        }

    }

}
