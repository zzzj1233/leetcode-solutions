package com.zzzj.window;

/**
 * @author zzzj
 * @create 2021-12-15 18:03
 */
public class Leet1004 {

    public static void main(String[] args) {
        // [0,0,1,1,1,0,0]
        // 0
        System.out.println(longestOnes(new int[]{0, 0, 1, 1, 1, 0, 0}, 2));
    }

    public static int longestOnes(int[] nums, int k) {
        int remain = k;

        int l = 0;
        int r = 0;

        int ans = 0;

        while (r < nums.length) {
            if (nums[r] == 0) {
                remain--;
            }

            if (remain < 0) {
                // 直到L跳过一个0
                while (l < nums.length && nums[l] == 1) {
                    l++;
                }
                l++;
                remain = 0;
            }

            ans = Math.max(ans, r - l + 1);
            r++;
        }

        return ans;
    }

}
