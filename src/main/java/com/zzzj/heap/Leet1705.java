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
        // 每天最多吃一个苹果,N天后可以继续吃,最多可以吃多少个苹果
        int N = apples.length;

        // 最先腐烂的苹果
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));

        int ans = 0;

        int day = 0;

        for (; day < N; day++) {
            if (days[day] != 0) {
                queue.add(new int[]{days[day] + day - 1, apples[day]});
            }
            while (!queue.isEmpty()) {
                int[] first = queue.peek();
                if (first[0] < day || first[1] == 0) {
                    queue.remove();
                    continue;
                }
                first[1]--;
                ans++;
                break;
            }
        }

        while (!queue.isEmpty()) {
            int[] first = queue.peek();
            if (first[0] < day || first[1] == 0) {
                queue.remove();
                continue;
            }
            first[1]--;
            ans++;
            day++;
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
