package com.zzzj.window;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-12-20 18:35
 */
public class Leet1248 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = LeetUtils.newArray(50);
            final int k = LeetUtils.random.nextInt(2) + 1;
            if (numberOfSubarrays(arr, k) != right(arr, k)) {
                System.out.println("error");
                System.out.println(Arrays.toString(arr));
                System.out.println(k);
                return;
            }
        }
        System.out.println(numberOfSubarrays(new int[]{9, 1, 2, 8, 4}, 2));
    }

    public static int numberOfSubarrays(int[] nums, int k) {

        int l = 0;

        int leastL = -1;

        int ans = 0;

        while (l < nums.length && (nums[l] & 1) == 0) {
            l++;
        }

        int r = l;

        int count = 0;

        while (r < nums.length) {
            if ((nums[r] & 1) == 1) {
                count++;
            }

            if (count > k) {
                leastL = l;
                l++;
                count--;
                while (l < nums.length && (nums[l] & 1) != 1) {
                    l++;
                }
            }

            if (count == k) {
                int previousR = r;
                while (r + 1 < nums.length && (nums[r + 1] & 1) != 1) {
                    r++;
                }
                ans += (l - leastL) * (r - previousR + 1);
            }

            r++;
        }

        return ans;
    }


    public static int right(int[] nums, int k) {
        int n = nums.length;
        int[] cnt = new int[n + 1];
        int odd = 0, ans = 0;
        cnt[0] = 1;
        for (int i = 0; i < n; ++i) {
            odd += nums[i] & 1;
            ans += odd >= k ? cnt[odd - k] : 0;
            cnt[odd] += 1;
        }
        return ans;
    }

}
