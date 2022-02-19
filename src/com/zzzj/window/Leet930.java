package com.zzzj.window;

/**
 * @author Zzzj
 * @create 2022-01-14 23:53
 */
public class Leet930 {

    public static void main(String[] args) {
        System.out.println(numSubarraysWithSum(new int[]{0, 0, 0, 0, 0}, 0));
        System.out.println(numSubarraysWithSum(new int[]{1, 0, 1, 0, 0, 1}, 2));
    }

    public static int numSubarraysWithSum(int[] nums, int goal) {
        int l = 0;
        int r = 0;

        int curSum = 0;

        int ans = 0;

        while (r < nums.length) {
            curSum += nums[r];

            // 缩小窗口
            while (curSum > goal && l < r) {
                curSum -= nums[l];
                l++;
            }

            // 看看r右边有多少个0
            if (curSum == goal) {
                ans++;
                int right = r + 1;

                while (right < nums.length && nums[right] == 0) {
                    right++;
                    ans++;
                }
                r = right;
            }

            r++;
        }

        return ans;
    }

}
