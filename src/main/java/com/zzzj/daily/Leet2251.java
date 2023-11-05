package com.zzzj.daily;

import cn.hutool.extra.tokenizer.engine.analysis.AnalysisEngine;
import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.function.BiFunction;

/**
 * @author zzzj
 * @create 2023-09-28 12:41
 */
public class Leet2251 {

    public static void main(String[] args) {

        BiFunction<int[][], int[], int[]> right = Leet2251::fullBloomFlowers;

        // BiFunction<int[][], int[], int[]> test = Leet2251::binarySearch;

        BiFunction<int[][], int[], int[]> test = Leet2251::diff;

        for (int i = 0; i < 10000; i++) {

            int N = LeetUtils.random.nextInt(1000) + 1;

            int M = LeetUtils.random.nextInt(1000) + 1;

            int rangeR = 10000;

            int[][] flowers = new int[N][2];
            int[] people = new int[M];

            for (int j = 0; j < N; j++) {
                int num1 = LeetUtils.random.nextInt(rangeR) + 1;
                int num2 = LeetUtils.random.nextInt(rangeR) + 1;
                flowers[j][0] = Math.min(num1, num2);
                flowers[j][1] = Math.max(num1, num2);
            }

            for (int j = 0; j < M; j++) {
                people[j] = LeetUtils.random.nextInt(rangeR) + 1;
            }

            int[] r1 = right.apply(ArrayUtil.copy(flowers), ArrayUtil.copy(people));

            int[] r2 = test.apply(ArrayUtil.copy(flowers), ArrayUtil.copy(people));

            if (!Arrays.equals(r1, r2)) {
                System.out.println("Error");
                return;
            }

        }

        System.out.println("Ok");
    }

    public static int[] fullBloomFlowers(int[][] flowers, int[] people) {

        int N = flowers.length;

        int M = people.length;

        int[] ans = new int[M];

        int[][] combined = new int[M][2];

        for (int i = 0; i < M; i++) {
            combined[i][0] = people[i];
            combined[i][1] = i;
        }

        Arrays.sort(combined, Comparator.comparingInt(o -> o[0]));

        Arrays.sort(flowers, Comparator.comparingInt(o -> o[0]));

        // 根据flowers[i][1]排序的小根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(index -> flowers[index][1]));

        int index = 0;

        for (int i = 0; i < M; i++) {

            int p = combined[i][0];

            while (!queue.isEmpty() && flowers[queue.peek()][1] < p)
                queue.remove();

            for (; index < N; index++) {

                int[] flower = flowers[index];

                int start = flower[0];

                int end = flower[1];

                // 什么时候结束? start > first的时候
                if (start > p)
                    break;

                // start <= first && end >= first
                if (end >= p)
                    queue.add(index);
            }

            ans[combined[i][1]] = queue.size();
        }

        return ans;
    }

    public static int[] binarySearch(int[][] flowers, int[] people) {

        int N = flowers.length;

        int[][] temp = new int[N][];

        for (int i = 0; i < N; i++)
            temp[i] = flowers[i];

        // 按照start排序
        Arrays.sort(flowers, Comparator.comparingInt(o -> o[0]));

        // 按照end排序
        Arrays.sort(temp, Comparator.comparingInt(o -> o[1]));

        int M = people.length;

        int[] ans = new int[M];

        for (int i = 0; i < M; i++) {

            int geStart = doSearch(flowers, 0, true, people[i]);
            int gtEnd = doSearch(temp, 1, false, people[i]);

            ans[i] = geStart - gtEnd;
        }

        return ans;
    }

    public static int doSearch(int[][] flowers, int compareIndex, boolean ge, int p) {
        int left = 0;
        int right = flowers.length - 1;

        int ans = -1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);

            if (ge ? flowers[mid][compareIndex] <= p : flowers[mid][compareIndex] < p) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return ans + 1;
    }

    public static int[] diff(int[][] flowers, int[] people) {

        TreeMap<Integer, Integer> diff = new TreeMap<>();

        for (int[] flower : flowers) {
            diff.put(flower[0], diff.getOrDefault(flower[0], 0) + 1);
            diff.put(flower[1] + 1, diff.getOrDefault(flower[1] + 1, 0) - 1);
        }

        int M = people.length;

        int[][] combined = new int[M][2];

        for (int i = 0; i < M; i++) {
            combined[i][0] = people[i];
            combined[i][1] = i;
        }

        Arrays.sort(combined, Comparator.comparingInt(o -> o[0]));

        int[] ans = new int[M];

        int curr = 0;

        for (int i = 0; i < M; i++) {

            int p = combined[i][0];

            while (!diff.isEmpty() && diff.firstKey() <= p) {
                curr += diff.pollFirstEntry().getValue();
            }

            ans[combined[i][1]] = curr;
        }

        return ans;
    }

}
