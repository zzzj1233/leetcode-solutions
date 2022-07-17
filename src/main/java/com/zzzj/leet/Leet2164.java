package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-07-10 18:49
 */
public class Leet2164 {


    public static int[] sortEvenOdd(int[] nums) {

        int[] bucket1 = new int[101];
        int[] bucket2 = new int[101];

        for (int i = 0; i < nums.length; i += 2) {
            bucket1[nums[i]]++;
        }

        for (int i = 1; i < nums.length; i += 2) {
            bucket2[nums[i]]++;
        }

        int index1 = 0;
        int index2 = 100;

        for (int i = 0; i < nums.length; i += 2) {
            while (bucket1[index1] == 0) {
                index1++;
            }
            nums[i] = index1;
            bucket1[index1]++;
        }

        for (int i = 1; i < nums.length; i += 2) {
            while (bucket2[index2] == 0) {
                index2--;
            }
            nums[i] = index2;
            bucket2[index2]--;
        }

        return nums;
    }


}
