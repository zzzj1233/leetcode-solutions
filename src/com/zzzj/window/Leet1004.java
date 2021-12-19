package com.zzzj.window;

/**
 * @author Zzzj
 * @create 2021-12-15 22:55
 */
public class Leet1004 {

    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{0, 0, 1, 1, 1, 0, 0}, 0));
    }

    public static int longestOnes(int[] nums, int k) {
        int ans = 0;

        int l = 0;
        int r = 0;

        int remainK = k;

        while (r < nums.length) {

            if (nums[r] == 0) {
                remainK--;
            }

            if (remainK <= 0) {
                // l pass 一个0
                if (nums[l] == 0) {
                    l++;
                } else {
                    while (nums[l] != 0) {
                        l++;
                    }
                }
                remainK++;
            }

            ans = Math.max(ans, r - l + 1);
            r++;
        }

        return ans;
    }


}
