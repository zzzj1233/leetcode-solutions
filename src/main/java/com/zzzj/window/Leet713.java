package com.zzzj.window;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-01-06 17:06
 */
public class Leet713 {

    public static void main(String[] args) {
        while (true) {
            int[] ints = ArrayUtil.generateArray(5, 1, 100);
            int k = LeetUtils.random.nextInt(200);

            final int ans = violent(ints, k);
            final int perhaps = numSubarrayProductLessThanK(ints, k);

            if (ans != perhaps && ans > 0) {
                System.out.println(Arrays.toString(ints));
                System.out.println(k);
                System.out.println(ans + " --- " + perhaps);
                return;
            }
        }
    }


    private static int violent(int[] nums, int k) {
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {

            int sum = 1;
            for (int j = i; j < nums.length; j++) {
                sum *= nums[j];
                if (sum < k) {
                    ans++;
                } else {
                    break;
                }
            }

        }

        return ans;
    }


    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0;

        int sum = 1;

        int r = 0;

        int l = 0;

        while (r < nums.length) {

            sum *= nums[r];

            while (sum >= k) {
                sum /= nums[l++];
                if (l >= nums.length) {
                    return ans;
                }
            }

            ans += r - l + 1;

            r++;
        }

        return ans;
    }

}
