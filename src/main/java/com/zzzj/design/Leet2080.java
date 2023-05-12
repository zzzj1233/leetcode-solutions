package com.zzzj.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-05-11 18:05
 */
public class Leet2080 {


    public static void main(String[] args) {
        RangeFreqQuery rq = new RangeFreqQuery(
                new int[]{1, 1, 1, 2, 2}
        );

        // System.out.println(rq.query(1, 2, 4));
        System.out.println(rq.query(0, 1, 2));
    }

    private static class RangeFreqQuery {

        Map<Integer, List<Integer>> index = new HashMap<>();

        public RangeFreqQuery(int[] arr) {

            int N = arr.length;

            for (int i = 0; i < N; i++) {
                index.computeIfAbsent(arr[i], integer -> new ArrayList<>())
                        .add(i);
            }

        }

        public int query(int left, int right, int value) {
            List<Integer> list = index.get(value);

            if (list == null) {
                return 0;
            }

            int N = list.size();

            if (left > list.get(N - 1) || right < list.get(0)) {
                return 0;
            }

            // find leftIndex
            int L = 0;
            int R = N - 1;

            int leftIndex = -1;

            while (L <= R) {
                int mid = L + ((R - L) >> 1);

                if (list.get(mid) >= left) {
                    leftIndex = mid;
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }

            int rightIndex = -1;

            L = 0;
            R = N - 1;

            while (L <= R) {
                int mid = L + ((R - L) >> 1);

                if (list.get(mid) <= right) {
                    rightIndex = mid;
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }

            return rightIndex - leftIndex + 1;
        }

    }

}
