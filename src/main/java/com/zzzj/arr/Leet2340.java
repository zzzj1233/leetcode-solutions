package com.zzzj.arr;

public class Leet2340 {

    public static void main(String[] args) {
        System.out.println(minimumSwaps(new int[]{3, 4, 5, 5, 3, 1}));
    }

    // [35,25,30,25,31,39,35]
    public static int minimumSwaps(int[] nums) {

        int N = nums.length;

        int minIndex = N - 1;
        int maxIndex = 0;

        for (int i = 0; i < N; i++) {
            if (nums[i] >= nums[maxIndex]) {
                maxIndex = i;
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            if (nums[i] <= nums[minIndex]){
                minIndex = i;
            }
        }

        if (minIndex == maxIndex) {
            return 0;
        }

        return minIndex + ((N - 1) - maxIndex) - (minIndex > maxIndex ? 1 : 0);
    }

}
