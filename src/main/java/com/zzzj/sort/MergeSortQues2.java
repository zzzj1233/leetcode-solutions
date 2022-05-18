package com.zzzj.sort;

import com.zzzj.util.ArrayUtil;

/**
 * @author zzzj
 * @create 2022-05-16 11:04
 */
public class MergeSortQues2 {

    public static void main(String[] args) {

//        int[] nums = {8, 7, 3, 4, 1, 3, 5, 8, 2};
//
//        System.out.println(violent(nums));
//        System.out.println(dfs(nums, 0, nums.length - 1));
//
//        System.exit(0);

        int N = 1000;

        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtil.generateArray(N, 1, 10000);

            if (violent(arr) != dfs(arr, 0, arr.length - 1)) {
                System.out.println("Error");
                return;
            }
        }

        System.out.println("Okay");
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

        int helpIdx = 0;

        int ans = 0;

        while (l <= mid && r <= j) {
            if ((nums[r] << 1) < nums[l]) {
                ans += mid - l + 1;
                r++;
            } else {
                l++;
            }
        }

        l = i;
        r = mid + 1;

        while (l <= mid && r <= j) {
            if (nums[l] < nums[r]) {
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

        System.arraycopy(help, 0, nums, i, N);

        return ans;
    }

    // 一个数组中 每个数右边有多少数✖️2比当前小 求个数
    public static int violent(int[] nums) {
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {

            int num = nums[i];

            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[j] << 1) < num) {
                    ans++;
                }
            }

        }

        return ans;
    }

}
