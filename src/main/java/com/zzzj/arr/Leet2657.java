package com.zzzj.arr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-05-05 11:55
 */
public class Leet2657 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findThePrefixCommonArray(
                new int[]{1, 3, 2, 4},
                new int[]{3, 1, 2, 4}
        )));

        System.out.println(Arrays.toString(findThePrefixCommonArray(
                new int[]{2, 3, 1},
                new int[]{3, 1, 2}
        )));
    }

    public static int[] findThePrefixCommonArray(int[] A, int[] B) {

        int N = A.length;

        int[] ans = new int[N];

        int cnt = 0;

        Map<Integer, Integer> mapA = new HashMap<>(N);

        Map<Integer, Integer> mapB = new HashMap<>(N);

        for (int i = 0; i < N; i++) {

            if (A[i] == B[i]) {
                cnt++;
            } else {

                Integer b = mapB.get(A[i]);

                if (b != null && b > 0) {
                    cnt++;
                    mapB.put(A[i], b - 1);
                } else {
                    mapA.put(A[i], mapA.getOrDefault(A[i], 0) + 1);
                }

                Integer a = mapA.get(B[i]);

                if (a != null && a > 0) {
                    cnt++;
                    mapA.put(B[i], a - 1);
                } else {
                    mapB.put(B[i], mapB.getOrDefault(B[i], 0) + 1);
                }

            }

            ans[i] = cnt;
        }

        return ans;
    }


}
