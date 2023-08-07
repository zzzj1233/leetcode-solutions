package com.zzzj.contest.dweek103;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zzzj
 * @create 2023-08-02 16:20
 */
public class Leet2657 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(findThePrefixCommonArray(new int[]{1, 3, 2, 4}, new int[]{3, 1, 2, 4})));

        System.out.println(Arrays.toString(findThePrefixCommonArray(new int[]{2, 3, 1}, new int[]{3, 1, 2})));

    }

    public static int[] findThePrefixCommonArray(int[] A, int[] B) {

        int N = A.length;

        int[] ans = new int[N];

        Set<Integer> set1 = new HashSet<>(N);

        Set<Integer> set2 = new HashSet<>(N);

        for (int i = 0; i < N; i++) {
            set1.add(A[i]);
            set2.add(B[i]);

            int cnt = 0;

            for (Integer num : set1) if (set2.contains(num)) cnt++;

            ans[i] = cnt;
        }

        return ans;
    }

}
