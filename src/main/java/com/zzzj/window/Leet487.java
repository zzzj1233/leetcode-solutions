package com.zzzj.window;

/**
 * @author zzzj
 * @create 2022-02-18 12:07
 */
public class Leet487 {

    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0}));
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1}));
    }

    public static int findMaxConsecutiveOnes(int[] nums) {

        int ans = 0;
        int l = 0;
        int r = 0;

        boolean reversible = true;

        while (r < nums.length) {
            if (nums[r] == 0) {
                if (reversible) {
                    reversible = false;
                } else {
                    while (nums[l] == 1) {
                        l++;
                    }
                    l++;
                    reversible = true;
                }
            }

            ans = Math.max(ans, r - l + 1);
            r++;
        }

        return ans;
    }

}
