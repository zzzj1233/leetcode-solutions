package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-07-30 14:49
 */
public class Leet137 {

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 3, 2}));
    }

    public static int singleNumber(int[] nums) {
        int[] bucket = new int[32];

        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if (((num >> i) & 1) == 1) {
                    bucket[i]++;
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < bucket.length; i++) {
            ans |= (bucket[i] % 3) << i;
        }

        return ans;
    }

}
