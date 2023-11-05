package com.zzzj.dp;

public class Leet1547 {

    public static void main(String[] args) {

        System.out.println(minCost(7, new int[]{1, 3, 4, 5}));

//        System.out.println(minCost(9, new int[]{5, 6, 1, 4, 2}));

    }

    public static int minCost(int n, int[] cuts) {

        int M = cuts.length;

        int[] arr = new int[M + 2];

        arr[0] = 0;

        for (int i = 1; i <= M; i++)
            arr[i] = cuts[i - 1];

        arr[M + 1] = n;

        return dfs(arr, 0, arr.length - 1);
    }

    public static int dfs(int[] arr, int left, int right) {

        if (left >= right)
            return 0;

        int cost = arr[right] - arr[left];

        int min = Integer.MAX_VALUE;

        for (int i = left; i < right; i++) {
            min = Math.min(
                    min,
                    dfs(arr, left, i) + dfs(arr, i + 1, right)
            );
        }

        return min + cost;
    }

}
