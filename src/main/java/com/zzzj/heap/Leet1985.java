package com.zzzj.heap;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

public class Leet1985 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtil.generateArray(1000, 1, 10000);
            String[] origin = new String[arr.length];
            for (int j = 0; j < arr.length; j++) {
                origin[j] = String.valueOf(arr[j]);
            }

            int k = LeetUtils.random.nextInt(arr.length) + 1;

            if (!kthLargestNumber(origin, k).equals(new Solution().kthLargestNumber(origin, k))) {
                System.out.println(LeetUtils.stringsToLeetCode(origin));
                System.out.println(k);
                System.out.println(kthLargestNumber(origin, k));
                System.out.println(new Solution().kthLargestNumber(origin, k));
                return;
            }
        }

        System.out.println("Ok");
    }

    public static String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, (a, b) -> {
            int la = a.length(), lb = b.length();
            if (la > lb) return -1;
            if (la < lb) return 1;
            return b.compareTo(a);
        });
        return nums[k - 1];
    }

    private static class Solution {
        public String kthLargestNumber(String[] nums, int k) {
            return kthLargestNumber(nums, 0, nums.length - 1, k);
        }

        private String kthLargestNumber(String[] nums, int L, int R, int k) {
            int randomIndex = (int) (Math.random() * (R - L + 1)) + L;
            int index = this.sort(nums, L, R, randomIndex);
            if (index == k - 1) {
                return nums[index];
            } else if (index < k - 1) {
                return this.kthLargestNumber(nums, index + 1, R, k);
            } else {
                return this.kthLargestNumber(nums, L, index - 1, k);
            }
        }

        private int sort(String[] nums, int L, int R, int index) {
            this.swap(nums, index, R);
            String num = nums[R];
            int p = L;
            for (int i = L; i < R; i++) {
                if (nums[i].length() > num.length() || (nums[i].length() == num.length() && nums[i].compareTo(num) >= 0)) {
                    this.swap(nums, p++, i);
                }
            }
            this.swap(nums, p, R);
            return p;
        }

        private void swap(String[] nums, int index, int r) {
            String tmp = nums[index];
            nums[index] = nums[r];
            nums[r] = tmp;
        }
    }

}
