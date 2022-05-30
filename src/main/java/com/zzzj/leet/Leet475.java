package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-05-21 13:16
 */
public class Leet475 {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(binSearch(new int[]{1, 3, 7, 14}, 2)));
//        System.out.println(Arrays.toString(binSearch(new int[]{1, 3, 7, 14}, 6)));
//        System.out.println(Arrays.toString(binSearch(new int[]{1, 3, 7, 14}, 9)));

        // System.out.println(findRadius(new int[]{100, 5, 7, 9, 11}, new int[]{1, 5}));
    }

    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        Arrays.sort(houses);

        int ans = 0;

        for (int i = 0; i < houses.length; i++) {
            int leftestIndex = binSearch(heaters, houses[i]);
            int rightIndex = leftestIndex + 1;
            int left = leftestIndex >= 0 ? houses[i] - heaters[leftestIndex] : houses[i];
            // int right = rightIndex< heaters.length ? heaters[rightIndex] - houses[i] :
            // ans = Math.max(ans, Math.min(left, right));
        }

        return ans;
    }

    public static int binSearch(int[] arr, int num) {
        int l = 0;
        int r = arr.length - 1;
        int mid;

        while (l < r) {
            mid = l + ((r - l) >> 1);
            if (arr[mid] > num) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

}
