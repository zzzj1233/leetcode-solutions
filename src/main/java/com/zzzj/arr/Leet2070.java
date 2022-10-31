package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2022-10-28 14:38
 */
public class Leet2070 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maximumBeauty(LeetUtils.convertInts("[[1,2],[3,2],[2,4],[5,6],[3,5]]"), new int[]{1, 2, 3, 4, 5, 6})));
    }

    public static int[] maximumBeauty(int[][] items, int[] queries) {

        Arrays.sort(items, Comparator.comparingInt(o -> o[0]));

        int N = items.length;

        int[] p = new int[N];

        p[0] = items[0][1];

        for (int i = 1; i < N; i++) {
            p[i] = Math.max(items[i][1], p[i - 1]);
        }

        int M = queries.length;

        int[] ans = new int[M];

        for (int i = 0; i < M; i++) {
            int query = queries[i];

            int index = binarySearch(items, query);

            if (index >= 0) {
                ans[i] = p[index];
            }

        }

        return ans;
    }

    public static int binarySearch(int[][] items, int price) {
        int low = 0;
        int high = items.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (items[mid][0] <= price) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low - 1;
    }

}
