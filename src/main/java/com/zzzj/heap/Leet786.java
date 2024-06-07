package com.zzzj.heap;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-03-11 17:31
 */
public class Leet786 {

    public static void main(String[] args) {

//        System.out.println(Arrays.toString(kthSmallestPrimeFraction(new int[]{1, 2, 3, 5, 7}, 7)));


        System.out.println(((double) 19259) / 20369);

        System.out.println(Arrays.toString(kthSmallestPrimeFraction(
                LeetUtils.convertInts1("[1,19,71,107,307,367,419,1009,1153,1297,1373,1693,1931,2389,2609,2731,2917,3461,3613,3677,4001,4013,4201,4513,4691,5323,5333,5503,6701,7283,7433,7621,7673,8053,8191,8387,9043,9239,9433,9923,10321,10627,10639,10723,11279,11411,11779,11801,12437,12473,12703,13799,13997,14051,14251,14653,14683,14759,14797,15091,15149,15217,16987,17467,18253,18541,18731,19051,19259,19813,19963,20149,20347,20369,20879,20899,21521,22079,22571,22709,22783,22859,23087,23567,23593,24847,24917,25117,25601,25903,26029,26407,26437,26573,27271,27803,27901,27961,28307,29017]"), 4733)));

    }


    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {

        int N = arr.length;

        double l = 0;

        double r = 1;

        int[] ans = new int[2];

        while (l + 1.0e-8 < r) {

            double mid = l + ((r - l) / 2.0);

            int c = check(arr, k, mid);

            if (c == k) {
                ans[0] = arr[a];
                ans[1] = arr[b];
                return ans;
            } else if (c > k)
                r = mid;
            else
                l = mid;
        }

        return ans;
    }

    static int a, b;

    public static int check(
            int[] arr,
            int k,
            double m
    ) {

        int left = 0;

        int right = 1;

        int N = arr.length;

        int c = 0;

        double large = 0;

        while (right < N) {

            double v = ((double) arr[left]) / arr[right];

            if (v <= m) {
                c += N - right;

                if (v > large) {
                    large = v;
                    a = left;
                    b = right;
                }

                left++;
                if (left == right)
                    right++;
            } else {
                right++;
            }

        }

        return c;
    }


    public static int[] kthSmallestPrimeFraction_PQ(int[] arr, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(arr.length << 1, Comparator.comparingDouble(o -> (double) o[0] / o[1]));

        for (int i = 0; i < arr.length; i++) {

            for (int j = arr.length - 1; j > i; j--) {
                queue.add(new int[]{arr[i], arr[j]});
            }

        }

        for (int i = 0; i < k - 1; i++) {
            queue.remove();
        }

        return queue.remove();
    }

}
