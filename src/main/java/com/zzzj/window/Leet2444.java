package com.zzzj.window;

/**
 * @author zzzj
 * @create 2023-08-25 14:34
 */
public class Leet2444 {

    public static void main(String[] args) {

        System.out.println(countSubarrays(new int[]{1, 3, 5, 2, 7, 5}, 1, 5));

    }

    // [1,2,3,1,3]
    // 1
    // 3
    public static long countSubarrays(int[] nums, int minK, int maxK) {

        int N = nums.length;

        long ans = 0;

        int minIndex = 0;
        int maxIndex = 0;
        int index = 0;

        while (minIndex < N && nums[minIndex] != minK) {
            minIndex++;
        }

        while (maxIndex < N && nums[maxIndex] != maxK) {
            maxIndex++;
        }

        index = Math.max(minIndex, maxIndex);

        while (index < N && minIndex < N && maxIndex < N) {

            if (nums[index] > maxK || nums[index] < minK) {
                maxIndex = index + 1;
                minIndex = index + 1;
            }

            int[] range = rangeMinMax(Math.min(minIndex, maxIndex), Math.max(minIndex, maxIndex), nums);

            while (nums[range[0]] < minK || nums[range[1]] > maxK) {

                if (range[0] < minK) {
                    minIndex = range[0] + 1;
                    while (minIndex < N && nums[minIndex] != minK) {
                        minIndex++;
                    }
                }

                if (range[1] > maxK) {
                    maxIndex = range[1] + 1;
                    while (maxIndex < N && nums[maxIndex] != maxK) {
                        maxIndex++;
                    }
                }

                index = Math.max(minIndex, maxIndex);
                range = rangeMinMax(Math.min(minIndex, maxIndex), Math.max(minIndex, maxIndex), nums);
            }

            index++;
        }

        return ans;
    }

    public static int[] rangeMinMax(int L, int R, int[] nums) {
        int min = L;
        int max = L;
        for (int i = L; i <= R; i++) {
            if (nums[i] < nums[min])
                min = i;
            if (nums[i] > nums[max])
                max = i;
        }
        return new int[]{min, max};
    }

}
