package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2022-09-22 17:59
 */
public class Leet2216 {

    public static void main(String[] args) {
        System.out.println(minDeletion(new int[]{1, 1, 2, 2, 3, 3}));
    }

    public static int minDeletion(int[] nums) {
        // N % 2 == 0

        // if i % 2 == 0 : nums[i] != nums[i + 1]
        int ans = 0;

        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (index % 2 == 0 && i + 1 < nums.length && nums[i] == nums[i + 1]) {
                ans++;
            } else {
                index++;
            }
        }

        return ans + (index % 2 == 0 ? 0 : 1);
    }

}
