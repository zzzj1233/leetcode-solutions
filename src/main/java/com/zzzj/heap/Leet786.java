package com.zzzj.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-03-11 17:31
 */
public class Leet786 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(kthSmallestPrimeFraction(new int[]{1, 2, 3, 5}, 3)));
    }

    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
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
