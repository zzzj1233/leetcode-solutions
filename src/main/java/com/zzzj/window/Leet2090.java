package com.zzzj.window;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-02-22 12:08
 */
public class Leet2090 {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int[] arr = ArrayUtil.generateArray(1000, 0, 10000);
            int k = LeetUtils.random.nextInt(100000);
            if (!Arrays.equals(getAverages(arr, k), right(arr, k))) {
                System.out.println(Arrays.toString(arr));
                System.out.println(k);
                return;
            }
        }
    }

    public static int[] getAverages(int[] nums, int k) {
        int[] ans = new int[nums.length];

        Arrays.fill(ans, -1);

        if (k > nums.length / 2) {
            return ans;
        }

        int windowSize = (k << 1) + 1;

        int l = 0;
        int r = windowSize - 1;

        long curSum = 0;

        for (int i = 0; i < r; i++) {
            curSum += nums[i];
        }

        for (int i = k; i + k < nums.length; i++) {
            curSum += nums[r];
            ans[i] = (int) (curSum / windowSize);
            curSum -= nums[l];
            l++;
            r++;
        }

        return ans;
    }

    public static int[] right(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n];
        // base case
        if (2 * k > n) {
            Arrays.fill(ans, -1);
            return ans;
        }
        int idx = 0;
        int L = 0;
        int R = 0;
        long sum = 0L;
        // 先给前面k个元素赋值为-1
        for (int i = 0; i < k; i++) {
            ans[idx++] = -1;
        }
        // 窗口内的元素
        while (R < n) {
            int addWindow = nums[R];
            R++;
            sum += addWindow * 1L;
            // 维持窗口长度为2 * k + 1
            while (R - L == 2 * k + 1) {
                ans[idx++] = (int) (sum / (2 * k + 1));
                int subWindow = nums[L];
                L++;
                sum -= subWindow * 1L;
            }
        }
        // 窗口外 后面的元素填-1
        for (int i = idx; i < n; i++) {
            ans[i] = -1;
        }
        return ans;
    }

}
