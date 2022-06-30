package com.zzzj.leet;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-06-30 11:24
 */
public class Leet658 {

    public static void main(String[] args) {
        // [1,1]
        //  1
        // 1
        System.out.println(findClosestElements(new int[]{1, 1}, 1, 1));
    }

    // [1]
    // 1
    // 0
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int[] indexes = binSearch(arr, x);

        int left = indexes[0] - 1;
        int right = indexes[1] + 1;

        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < Math.min((right - left - 1), k); i++) {
            list.add(x);
        }

        while (list.size() < k) {
            if (left < 0) {
                list.addLast(arr[right++]);
            } else if (right >= arr.length) {
                list.addFirst(arr[left--]);
            } else {
                // |a - x| < |b - x| 或者
                // |a - x| == |b - x| 且 a < b
                if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                    list.addFirst(arr[left--]);
                } else {
                    list.addLast(arr[right++]);
                }
            }
        }

        return list;
    }


    // [0,1,1,1,2,3,6,7,8,9]
    // 9
    // 4
    // 输出：
    // [0,1,1,1,2,3,6,6,7]
    // 预期结果：
    // [0,1,1,1,2,3,6,7,8]
    public static int[] binSearch(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);

            if (arr[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }


        int l2 = 0;
        int r2 = arr.length - 1;

        while (l2 <= r2) {
            int mid = l2 + ((r2 - l2) >> 1);

            if (arr[mid] >= target) {
                r2 = mid - 1;
            } else {
                l2 = mid + 1;
            }
        }

        return new int[]{l2, r};
    }

}
