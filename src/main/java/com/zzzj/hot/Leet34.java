package com.zzzj.hot;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-04-23 20:10
 */
public class Leet34 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtil.generateArray(1000, 0, 10000);
            Arrays.parallelSort(arr);
            int index = LeetUtils.random.nextInt(arr.length);
            int target;
            if (index % 5 == 0) {
                target = LeetUtils.random.nextInt(1000);
            } else {
                target = arr[index];
            }
            if (!Arrays.equals(right(arr, target), searchRange(arr, target))) {
                System.out.println(Arrays.toString(arr));
                System.out.println(target);
                System.out.println("Error");
                return;
            }
        }

    }

    public static int[] right(int[] nums, int target) {
        int[] ans = {-1, -1};

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                ans[0] = i;
                break;
            }
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == target) {
                ans[1] = i;
                break;
            }
        }

        return ans;
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] ans = {-1, -1};

        // 2分到无法二分

        int l = 0;
        int r = nums.length - 1;
        int mid = (r + l) / 2;

        while (l <= r) {
            if (nums[mid] == target) {
                // 往左或者往右
                ans[0] = search(nums, target, 0, mid, true);
                ans[1] = search(nums, target, mid, r, false);
                return ans;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
            mid = (r + l) / 2;
        }


        return ans;
    }

    public static int search(int[] nums, int target, int l, int r, boolean findLeft) {
        int mid = (r + l) / 2;

        int result = -1;

        while (l <= r) {
            if (nums[mid] == target) {
                result = mid;
                if (findLeft) {
                    r = mid - 1;
                } else {
                    // 往右找
                    l = mid + 1;
                }
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
            mid = (r + l) / 2;
        }

        return result;
    }

}
