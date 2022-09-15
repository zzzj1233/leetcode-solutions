package com.zzzj.greedy;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-08 16:41
 */
public class Leet1564 {

    // [4,4,1,1]
    // [5,4,3,3,1]
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr1 = ArrayUtil.generateArray(1000, 1, 1000);
            int[] arr2 = ArrayUtil.generateArray(1000, 1, 1000);

            if (maxBoxesInWarehouse(arr1, arr2) != right(arr1, arr2)) {
                System.out.println("Error");
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        Arrays.sort(boxes);

        int N = warehouse.length;

        int[] min = new int[N];

        min[0] = warehouse[0];

        for (int i = 1; i < N; i++) {
            min[i] = Math.min(warehouse[i], min[i - 1]);
        }

        int ans = 0;

        for (int i = N - 1; i >= 0; i--) {
            int count = binarySearch(boxes, min[i]);

            if (count > ans) {
                ans++;
            }

        }

        return ans;
    }

    public static int binarySearch(int[] boxes, int capacity) {
        int L = 0;
        int R = boxes.length - 1;

        while (L <= R) {
            int mid = L + ((R - L) >> 1);

            if (boxes[mid] <= capacity) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return R + 1;
    }

    public static int right(int[] boxes, int[] warehouse) {
        Arrays.sort(boxes);

        // replace the height of each warehouse with
        // the minimum height before it (including itself)
        int min = warehouse[0];
        for (int ii = 0; ii < warehouse.length; ii++) {
            if (warehouse[ii] < min) min = warehouse[ii];
            warehouse[ii] = min;
        }

        // from the back of the warehouse, push boxes
        int boxIdx = 0;
        int count = 0;
        for (int ii = warehouse.length - 1; ii >= 0; ii--) {
            if (boxIdx == boxes.length) return boxes.length;
            if (warehouse[ii] >= boxes[boxIdx]) {
                count++;
                boxIdx++;
            }
        }

        return count;
    }

}
