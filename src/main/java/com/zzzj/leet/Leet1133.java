package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-07-22 14:22
 */
public class Leet1133 {

    public static int largestUniqueNumber(int[] nums) {
        int[] bucket = new int[1001];

        for (int num : nums) {
            bucket[num]++;
        }

        int N = bucket.length;

        for (int i = N - 1; i >= 0; i--) {
            if (bucket[i] == 1) {
                return i;
            }
        }
        return -1;
    }

}
