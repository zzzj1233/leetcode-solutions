package com.zzzj.window;

/**
 * @author zzzj
 * @create 2022-01-20 15:56
 */
public class Leet2134 {

    public static void main(String[] args) {
        System.out.println(minSwaps(new int[]{0, 1, 0, 1, 1, 0, 0}));
        System.out.println(minSwaps(new int[]{0, 1, 1, 1, 0, 0, 1, 1, 0}));
        System.out.println(minSwaps(new int[]{1, 1, 0, 0, 1}));
    }

    public static int minSwaps(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        int len = nums.length << 1;

        int l = 0;
        int r = 0;

        int ans = 0;

        while (r < count) {
            if (nums[r % nums.length] == 0) {
                ans++;
            }
            r++;
        }

        int window = ans;

        while (r < len) {
            if (nums[l % nums.length] == 0) {
                window--;
            }

            if (nums[r % nums.length] == 0) {
                window++;
            }

            ans = Math.min(ans, window);
            l++;
            r++;
        }

        return ans;
    }

}
