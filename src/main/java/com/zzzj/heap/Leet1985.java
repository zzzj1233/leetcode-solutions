package com.zzzj.heap;

import com.zzzj.util.ArrayUtil;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Leet1985 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtil.generateArray(1000, 1, 10000);
            String[] origin = new String[arr.length];
            for (int j = 0; j < arr.length; j++) {
                origin[j] = String.valueOf(arr[j]);
            }

        }
    }

    public static String kthLargestNumber(String[] nums, int k) {

        int N = nums.length;

        PriorityQueue<Integer> queue;

        boolean big = true;

        if (k <= N / 2) {
            queue = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        } else {
            queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
            k = N - k + 1;
            big = false;
        }

        for (int i = 0; i < N; i++) {
            if (queue.size() < k) {
                queue.add(Integer.parseInt(nums[i]));
            } else {
                int num = Integer.parseInt(nums[i]);

                int compareResult = num - queue.peek();
                if (big) {
                    if (compareResult > 0) {
                        queue.remove();
                        queue.add(num);
                    }
                } else {
                    if (compareResult <= 0) {
                        queue.remove();
                        queue.add(num);
                    }
                }
            }
        }

        return String.valueOf(queue.peek());
    }

    class Solution {
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
