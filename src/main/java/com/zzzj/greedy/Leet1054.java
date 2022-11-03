package com.zzzj.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-11-03 14:29
 */
public class Leet1054 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(rearrangeBarcodes(new int[]{1, 1, 1, 1, 2, 2, 3, 3})));
    }

    public static int[] rearrangeBarcodes(int[] barcodes) {

        int N = barcodes.length;

        Map<Integer, Integer> bucket = new HashMap<>();

        for (int barcode : barcodes) {
            bucket.put(barcode, bucket.getOrDefault(barcode, 0) + 1);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o2[1] == o1[1]) {
                return o1[2] - o2[2];
            }
            return o2[1] - o1[1];
        });

        for (Map.Entry<Integer, Integer> entry : bucket.entrySet()) {
            queue.add(new int[]{entry.getKey(), entry.getValue(), 0});
        }

        int[] ans = new int[N];

        int index = 0;

        int globalId = 0;

        int pre = -1;

        while (!queue.isEmpty()) {
            int[] max = queue.remove();

            if (max[0] != pre) {
                ans[index++] = max[0];
                max[1]--;
                max[2] = globalId++;
                pre = max[0];
            }

            if (!queue.isEmpty()) {
                ans[index++] = queue.peek()[0];
                queue.peek()[1]--;
                queue.peek()[2] = globalId++;
                pre = queue.peek()[0];
                if (queue.peek()[1] == 0) {
                    queue.remove();
                }
            }

            if (max[1] > 0) {
                queue.add(max);
            }
        }

        return ans;
    }

}
