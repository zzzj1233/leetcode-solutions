package com.zzzj.window;

/**
 * @author zzzj
 * @create 2021-12-31 14:07
 */
public class Leet930 {

    public static void main(String[] args) {
        System.out.println(numSubarraysWithSum(new int[]{0, 0, 0, 0, 0}, 0));
    }


    public static int numSubarraysWithSum(int[] nums, int goal) {
        int l = 0;
        int r = 0;
        int leastL = -1;

        int curSum = 0;

        int ans = 0;

        while (r < nums.length) {

            curSum += nums[r];

            if (curSum > goal) {
                while (l < r) {
                    curSum -= nums[l];
                    l++;
                }
            }

            if (curSum == goal) {
                // 看看右边有多少个0
                int prevR = r;
                while (r + 1 < nums.length && nums[r + 1] == 0) {
                    r++;
                }
                ans += (l - leastL) * (r - prevR + 1);
            }

            r++;
        }

        return ans;
    }


    /**
     * @param nums
     * @param nums
     * @param nums
     * @param nums
     * @param nums
     * @param nums
     * @param nums
     * @param nums
     * @param nums
     * @param nums
     * @param nums
     * @param nums
     * @param nums
     * @param nums
     * @param nums
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
