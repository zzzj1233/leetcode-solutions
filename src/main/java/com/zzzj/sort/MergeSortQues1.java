package com.zzzj.sort;

import com.zzzj.util.ArrayUtil;


/**
 * @author zzzj
 * @create 2022-05-16 11:04
 */
public class MergeSortQues1 {

    public static void main(String[] args) {
        int N = 1000;

        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtil.generateArray(N, 1, 1000);

            if (violent(arr) != leftMinSum(arr)) {
                System.out.println("Error");
                return;
            }
        }

        System.out.println("Okay");
    }

    // 一个数组中 每个数左边有多少数比当前小 求sum
    public static int leftMinSum(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }


    public static int dfs(int[] nums, int i, int j) {
        if (i == j) {
            return 0;
        }
        int mid = i + ((j - i) >> 1);
        return dfs(nums, i, mid) + dfs(nums, mid + 1, j) + merge(nums, i, mid, j);
    }

    public static int merge(int[] nums, int i, int mid, int j) {
        int N = j - i + 1;
        int[] help = new int[N];

        int l = i;
        int r = mid + 1;

        int result = 0;

        int helpIdx = 0;
        while (l <= mid && r <= j) {
            if (nums[l] < nums[r]) {
                result += nums[l] * (j - r + 1);
                help[helpIdx++] = nums[l++];
            } else {
                help[helpIdx++] = nums[r++];
            }
        }

        while (l <= mid) {
            help[helpIdx++] = nums[l++];
        }

        while (r <= j) {
            help[helpIdx++] = nums[r++];
        }

        for (int k = 0; k < N; k++) {
            nums[i + k] = help[k];
        }

        return result;
    }

    public static int violent(int[] nums) {

        int ans = 0;

        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < num) {
                    ans += nums[j];
                }
            }
        }

        return ans;
    }

}
