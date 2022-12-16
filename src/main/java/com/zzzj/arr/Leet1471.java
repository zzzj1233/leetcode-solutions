package com.zzzj.arr;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-11-25 11:20
 */
public class Leet1471 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getStrongest(new int[]{1, 2, 3, 4, 5}, 2)));
    }

    public static int[] getStrongest(int[] arr, int k) {

        int N = arr.length;

        Arrays.sort(arr);

        int middle = arr[(N - 1) >> 1];

        int[][] boxed = new int[N][2];

        for (int i = 0; i < N; i++) {
            boxed[i][0] = i;
            boxed[i][1] = arr[i];
        }

        List<Integer> list = Arrays.stream(boxed).sorted((o1, o2) -> {
                    int sub = Math.abs(o2[1] - middle) - Math.abs(o1[1] - middle);
                    if (sub != 0) {
                        return sub;
                    }
                    return o2[0] - o1[0];
                }).map(ints -> ints[1])
                .limit(k)
                .collect(Collectors.toList());

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

}
