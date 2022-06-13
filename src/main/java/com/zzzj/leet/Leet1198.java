package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-07 18:55
 */
public class Leet1198 {

    // 最小公共元素
    // 每一行都是递增
    public static int smallestCommonElement(int[][] mat) {
        int[] arr = mat[0];

        OUTER:
        for (int i = 0; i < arr.length; i++) {
            int search = arr[i];

            for (int j = 1; j < mat.length; j++) {
                if (!binarySearch(arr, search)) {
                    continue OUTER;
                }
            }

            return search;
        }

        return -1;
    }

    public static boolean binarySearch(int[] arr, int search) {
        int l = 0;
        int r = arr.length - 1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] == search) {
                return true;
            } else if (arr[mid] > search) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }

}
