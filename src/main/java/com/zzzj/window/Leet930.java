package com.zzzj.window;

import com.zzzj.util.Unresolved;

/**
 * @author zzzj
 * @create 2021-12-31 14:07
 */
@Unresolved
public class Leet930 {

    public static void main(String[] args) {
        System.out.println(numSubarraysWithSum(new int[]{0, 0, 0, 0, 0}, 0));
    }


    public static int numSubarraysWithSum(int[] nums, int goal) {
        int N = nums.length;

        int[] counter = new int[N];

        int sum = 0;

        int ans = 0;

        for (int i = 0; i < N; i++) {
            sum += nums[i];
            counter[sum]++;
            ans += counter[sum - goal];
        }

        return ans;
    }


    /**
     * @param nums
     * @param goal
     * @return
     */

    public int right(int[] nums, int goal) {
        int n = nums.length;
        int left1 = 0, left2 = 0, right = 0;
        int sum1 = 0, sum2 = 0;
        int ret = 0;
        while (right < n) {
            sum1 += nums[right];
            while (left1 <= right && sum1 > goal) {
                sum1 -= nums[left1];
                left1++;
            }
            sum2 += nums[right];
            while (left2 <= right && sum2 >= goal) {
                sum2 -= nums[left2];
                left2++;
            }
            ret += left2 - left1;
            right++;
        }
        return ret;
    }

}
