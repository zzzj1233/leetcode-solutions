package com.zzzj.heap;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-03-23 17:19
 */
public class Leet1705 {

    public static void main(String[] args) {
//        System.out.println(eatenApples(new int[]{1, 2, 3, 5, 2}, new int[]{3, 2, 1, 4, 2}));
//        System.out.println(eatenApples(new int[]{3, 0, 0, 0, 0, 2}, new int[]{3, 0, 0, 0, 0, 2}));
//
//        System.exit(0);

        for (int i = 0; i < 10000; i++) {
            int n = LeetUtils.random.nextInt(10) + 1;

            int[] apples = ArrayUtil.generateArray(n, 1, n);
            int[] days = ArrayUtil.generateArray(n, 1, n);

            int[] copy = Arrays.copyOfRange(apples, 0, apples.length);

            if (eatenApples(apples, days) != right(copy, days)) {
                System.out.println(Arrays.toString(copy));
                System.out.println(Arrays.toString(days));
                System.out.println(eatenApples(copy, days));
                return;
            }
        }

    }

    public static int eatenApples(int[] apples, int[] days) {
        int ans = 0;

        int N = apples.length;

        PriorityQueue<int[]> queue = new PriorityQueue<>(N, Comparator.comparingInt(o -> o[1]));

        for (int i = 0; i < N; i++) {
            int apple = apples[i];
            if (apple == 0) {
                continue;
            }
            int day = days[i] + i;
            queue.add(new int[]{apple, day});
        }

        while (!queue.isEmpty()) {
            int apple = queue.peek()[0];
            int day = queue.peek()[1];

            if (ans + 1 > day) {
                queue.remove();
                continue;
            }

            queue.peek()[0] = apple - 1;

            // apple > 0
            if (apple - 1 == 0) {
                queue.remove();
            }

            // [1, 4, 1, 2, 1, 3]
            // [5, 3, 2, 4, 5, 6]
            ans++;
        }

        return ans;
    }

    public static int right(int[] apples, int[] days) {
        int ans = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        int n = apples.length;
        int i = 0;
        while (i < n) {
            while (!pq.isEmpty() && pq.peek()[0] <= i) {
                pq.poll();
            }
            int rottenDay = i + days[i];
            int count = apples[i];
            if (count > 0) {
                pq.offer(new int[]{rottenDay, count});
            }
            if (!pq.isEmpty()) {
                int[] arr = pq.peek();
                arr[1]--;
                if (arr[1] == 0) {
                    pq.poll();
                }
                ans++;
            }
            i++;
        }
        while (!pq.isEmpty()) {
            while (!pq.isEmpty() && pq.peek()[0] <= i) {
                pq.poll();
            }
            if (pq.isEmpty()) {
                break;
            }
            int[] arr = pq.poll();
            int curr = Math.min(arr[0] - i, arr[1]);
            ans += curr;
            i += curr;
        }
        return ans;
    }

}
