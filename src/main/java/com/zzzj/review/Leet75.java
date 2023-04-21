package com.zzzj.review;

/**
 * @author zzzj
 * @create 2023-03-15 17:48
 */
public class Leet75 {

    public static void sortColors(int[] nums) {

        int N = nums.length;

        int[] bucket = new int[3];

        for (int i = 0; i < N; i++) {
            int num = nums[i];
            bucket[num]++;
        }

        int index = 0;

        for (int i = 0; i < N; i++) {
            while (bucket[index] == 0) {
                index++;
            }
            nums[i] = index;
            bucket[index]--;
        }
    }

}
