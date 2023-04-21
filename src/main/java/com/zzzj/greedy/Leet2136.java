package com.zzzj.greedy;

import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-03-01 17:25
 */
public class Leet2136 {


    public static void main(String[] args) {
        int N = 10000;

        for (int i = 0; i < N; i++) {

            int n = 300;

            int[] pt = ArrayUtil.generateArray(n, 1, 1000);
            int[] gt = ArrayUtil.generateArray(n, 1, 1000);

            ArrayCopyIterator it1 = new ArrayCopyIterator(pt);
            ArrayCopyIterator it2 = new ArrayCopyIterator(gt);

            if (earliestFullBloom(it1.next(), it2.next()) != right(it1.next(), it2.next())) {
                System.out.println("Error");
                System.out.println(Arrays.toString(it1.next()));
                System.out.println(Arrays.toString(it2.next()));
                System.out.println("my ans : " + earliestFullBloom(it1.next(), it2.next()));
                System.out.println(right(it1.next(), it2.next()));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static int earliestFullBloom(int[] plantTime, int[] growTime) {

        PriorityQueue<Integer> queue = new PriorityQueue<>((index1, index2) -> {
            int p1 = plantTime[index1];
            int g1 = growTime[index1];

            int p2 = plantTime[index2];
            int g2 = growTime[index2];

            return g2 - g1;
        });

        int N = plantTime.length;

        for (int i = 0; i < N; i++) {
            queue.add(i);
        }

        int ans = 0;

        int cur = 0;

        while (!queue.isEmpty()) {
            Integer index = queue.remove();

            int pt = plantTime[index];

            int gt = growTime[index];

            ans = Math.max(ans, pt + cur + gt);

            cur += pt;
        }

        return ans;
    }

    public static int right(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        int[][] arr = new int[n][2];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            arr[i][0] = plantTime[i];
            arr[i][1] = growTime[i];
        }
        Arrays.sort(arr, (a, b) -> b[1] - a[1]);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i][0];
            ans = Math.max(ans, sum + arr[i][1]);
        }
        return ans;
    }

}
