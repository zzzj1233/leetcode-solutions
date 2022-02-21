package com.zzzj.window;

/**
 * @author zzzj
 * @create 2022-02-18 12:07
 */
public class Leet487 {

    public static void main(String[] args) {
//        for (int i = 0; i < 1000; i++) {
//            int[] arr = LeetUtils.randomBinaryArr(10);
//            if (findMaxConsecutiveOnes(arr) != right(arr)) {
//                System.out.println(Arrays.toString(arr));
//                System.out.println("Error");
//                return;
//            }
//        }
        System.out.println(findMaxConsecutiveOnes(new int[]{0, 0, 1, 1, 1, 0, 0, 1, 0, 0}));
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

    public static int right(int[] nums) {
        int n = nums.length;
        // 窗口的两个边界，以及 cnt 用来记录出现 0 的个数
        int left = 0, right = 0, cnt = 0;
        // 返回值
        int ans = 0;
        while (right < n) {
            // 只要右边界不越界，而且0的个数小于等于 1 个，那么就扩充右边界
            while (right < n && cnt <= 1) {
                if (nums[right] == 0) cnt++;
                right++;
                // 及时更新返回值
                if (cnt <= 1) ans = Math.max(right - left, ans);
            }
            // 向右缩小左边界，直到 0 的个数小于等于 1
            while (left <= right && cnt > 1) {
                if (nums[left] == 0) cnt--;
                left++;
            }
        }
        return ans;
    }


}
