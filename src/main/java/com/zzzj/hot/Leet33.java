package com.zzzj.hot;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Zzzj
 * @create 2022-04-23 19:10
 */
public class Leet33 {


    public static void main(String[] args) {

//        int[] nums = {9, 10, 12, 17, 21, 33, 34, 38, 90, 5};
//        int t = 5;
//        System.out.println(search(nums, t));
//
//        System.exit(0);

        for (int i = 0; i < 10000; i++) {
            int[] arr = randomArray(1000, 0, 1000);
            int index = LeetUtils.random.nextInt(arr.length);
            int target;
            if (index % 5 == 0) {
                target = LeetUtils.random.nextInt(1000);
            } else {
                target = arr[index];
            }
//            System.out.println(Arrays.toString(arr));
//            System.out.println(target);
//            search(arr, target);
            if (right(arr, target) != search(arr, target)) {
                System.out.println(Arrays.toString(arr));
                System.out.println(target);
                return;
            }
        }

    }

    public static int[] randomArray(int N, int rangL, int rangR) {
        // 1. 没有重复
        // 2. 旋转
        Set<Integer> used = new HashSet<>(N);

        int[] result = new int[N];

        for (int i = 0; i < N; i++) {
            int num = LeetUtils.random.nextInt(rangR) + rangL;
            while (used.contains(num)) {
                num = LeetUtils.random.nextInt(rangR) + rangL;
            }
            used.add(num);
            result[i] = num;
        }

        Arrays.parallelSort(result);

        int rotateIndex = LeetUtils.random.nextInt(N);

        int[] newResult = new int[N];

        int[] left = Arrays.copyOfRange(result, rotateIndex, N);

        for (int i = 0; i < left.length; i++) {
            newResult[i] = left[i];
        }

        for (int i = left.length; i < N; i++) {
            newResult[i] = result[i - left.length];
        }

        return newResult;
    }

    public static int right(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int mid = (r + l) / 2;

        // 如果 (l < mid || mid + 1 < r ) 证明数组没有被旋转

        // 没有被旋转
        while (l <= r) {
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[l] <= nums[mid]) {
                // 在这个区域找
                if (target >= nums[l] && target <= nums[mid]) {
                    return binarySearch(nums, l, mid, target);
                    // 在mid + 1 ~ r区域找
                } else {
                    l = mid + 1;
                }
                // 被旋转了
            } else {
                // 继续找mid // 找4
                // e.g. 3 4 5 6 7 1 2

                // mid ~ r 一定有序
                if (target >= nums[mid] && target <= nums[r]) {
                    return binarySearch(nums, mid, r, target);
                } else {
                    r = mid - 1;
                }
            }
            mid = (r + l) / 2;
        }

        return -1;
    }

    public static int binarySearch(int[] nums, int l, int r, int target) {
        int mid = (r + l) / 2;
        while (l <= r) {
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
            mid = (r + l) / 2;
        }
        return -1;
    }

}
