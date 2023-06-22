package com.zzzj.dp;

import java.util.Arrays;

public class Leet1387 {

    public static void main(String[] args) {
        System.out.println(getKth(12, 15, 2));

        System.out.println(getKth(7, 11, 4));
    }

    public static int getKth(int lo, int hi, int k) {

        int[] memo = new int[2000005];

        int[][] arr = new int[hi - lo + 1][2];

        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = weight(lo + i, memo);
            arr[i][1] = lo + i;
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        return arr[k - 1][1];
    }

    public static int weight(int num, int[] memo) {
        if (num == 1) {
            return 0;
        }

        if (memo[num] != 0) {
            return memo[num];
        }

        if (num % 2 == 0) {
            memo[num] = weight(num / 2, memo) + 1;
        } else {
            memo[num] = weight(3 * num + 1, memo) + 1;
        }

        return memo[num];
    }
}
