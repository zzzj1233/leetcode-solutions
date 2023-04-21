package com.zzzj.review;

/**
 * @author zzzj
 * @create 2023-03-09 18:02
 */
public class Leet33 {

    public static void main(String[] args) {
        System.out.println(search(new int[]{5, 1, 3}, 5));
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 8));
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    }

    public static int search(int[] nums, int target) {

        int N = nums.length;

        int left = 0;
        int right = N - 1;

        // 4,5,6,7,0,1,2
        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            int cur = nums[mid];

            if (cur == target) {
                return mid;
            }

            // 这一段有序
            if (nums[left] < nums[right]) {
                if (target > cur) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                // 这一段无序
                if (cur < target) {
                    // case1: bb[b]bssss , 目标在当前元素右边
                    // case2: bbbbs[s]ss , 目标在当前元素左边, 也可能在右边
                    int rightest = rightest(nums, mid, right);

                    if (target <= nums[rightest]) {
                        right = rightest;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                } else { // cur < target
                    // case1: bb[b]bssss , 可能在左边,也可能在右边
                    // case2: bbbbs[s]ss , 一定在左边
                    int leftest = leftest(nums, left, mid);

                    if (target >= nums[leftest]) {
                        left = leftest;
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }

                }
            }

        }


        return -1;
    }

    public static int leftest(int[] nums, int left, int right) {
        int target = nums[right];

        int result = -1;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (nums[mid] > target) {
                left = mid + 1;
            } else {
                result = mid;
                right = mid - 1;
            }

        }

        return result;
    }

    public static int rightest(int[] nums, int left, int right) {
        int target = nums[left];

        int result = -1;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (nums[mid] < target) {
                right = mid - 1;
            } else {
                result = mid;
                left = mid + 1;
            }

        }

        return result;
    }

}
