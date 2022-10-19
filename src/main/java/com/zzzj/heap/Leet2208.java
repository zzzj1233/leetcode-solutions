package com.zzzj.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Leet2208 {

    public static void main(String[] args) {
        System.out.println(halveArray(new int[]{5, 19, 8, 1}));
    }

    public static int halveArray(int[] nums) {
        PriorityQueue<Double> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        double sum = 0;

        for (int num : nums) {
            sum += num;
            maxHeap.add((double) num);
        }

        double target = sum / 2;

        int ans = 0;

        while (sum > target) {
            double max = maxHeap.remove() / 2;
            sum -= max;
            maxHeap.add(max);
            ans++;
        }

        return ans;
    }

}
