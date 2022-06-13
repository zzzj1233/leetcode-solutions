package com.zzzj.daily;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-07 17:17
 */
public class Leet875 {

    public static void main(String[] args) {

        System.out.println(minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        System.out.println(minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
        System.out.println(minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));

        int N = 10;
        int rangeR = 10000;

        for (int i = 0; i < 1000; i++) {
            int[] arr = ArrayUtil.generateArray(N, 1, rangeR);
            int[] copy = Arrays.copyOfRange(arr, 0, arr.length);
            int[] copy2 = Arrays.copyOfRange(arr, 0, arr.length);
            int h = LeetUtils.random.nextInt(N) + arr.length;
            if (minEatingSpeed(arr, h) != right(copy, h)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(copy2));
                System.out.println(h);
                return;
            }
        }

//        long start = System.currentTimeMillis();
//
//        for (int i = 0; i < 10000; i++) {
//            int[] arr = ArrayUtil.generateArray(N, 1, rangeR);
//            int h = LeetUtils.random.nextInt(N) + arr.length;
//            minEatingSpeed(arr, h);
//        }
//
//        System.out.println(System.currentTimeMillis() - start);
//
//        start = System.currentTimeMillis();
//
//        for (int i = 0; i < 10000; i++) {
//            int[] arr = ArrayUtil.generateArray(N, 1, rangeR);
//            int h = LeetUtils.random.nextInt(N) + arr.length;
//            right(arr, h);
//        }
//
//        System.out.println(System.currentTimeMillis() - start);
    }

    // 可以吃h个小时
    // 返回每小时最少吃几根香蕉,可以吃完所有的香蕉
    public static int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);

        int max = Arrays.stream(piles).max().getAsInt();

        int l = 1;
        int r = max;

        OUTER:
        while (l <= r) {
            int search = l + ((r - l) >> 1);

            // 找到N的倍数的位置
            int cost = 0;

            for (int i = 0; i < piles.length; i++) {
                if (piles[i] % search == 0) {
                    cost += piles[i] / search;
                } else {
                    cost += piles[i] / search + 1;
                }

                if (cost > h) {
                    l = search + 1;
                    continue OUTER;
                }
            }

            r = search - 1;
        }

        return r + 1;
    }


    public static int binSearch(int[] arr, int search) {
        int l = 0;
        int r = arr.length - 1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] <= search) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }

    public static int right(int[] piles, int h) {
        int len = piles.length;
        int maxNum = 0;
        int minNum = 1;
        int res = 0;
        for (int i = 0; i < len; i++) {
            maxNum = Math.max(maxNum, piles[i]);
        }
        if (h == len) {
            return maxNum;
        }
        while (minNum <= maxNum) {   //二分法找合适的
            int mid = minNum + (maxNum - minNum) / 2;
            int temp = h;
            for (int i = 0; i < len; i++) {
                temp -= getCeil(piles[i], mid);   //向上取整
                if (temp < 0 && i < len) {   //小了，应该扩大
                    minNum = mid + 1;
                }
                if (temp >= 0 && i == len - 1) {   //大了，试试缩小
                    maxNum = mid - 1;
                    res = mid;
                    break;
                }
            }
        }
        return res;
    }

    public static int getCeil(int a, int b) {  //向上取整
        if (a % b == 0) {
            return (int) a / (int) b;
        }
        return (int) a / (int) b + 1;
    }

}
