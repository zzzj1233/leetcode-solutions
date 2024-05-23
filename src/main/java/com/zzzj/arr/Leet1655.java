package com.zzzj.arr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2024-02-27 15:42
 */
public class Leet1655 {

    public static void main(String[] args) {

        System.out.println(canDistribute(new int[]{1, 2, 3, 4}, new int[]{2}));

    }

    public static boolean canDistribute(int[] nums, int[] quantity) {

        int[] arr = new int[51];

        Map<Integer, Integer> index = new HashMap<>();

        int global = 0;

        for (int num : nums) {
            if (!index.containsKey(num))
                index.put(num, global++);
            int idx = index.get(num);
            arr[idx]++;
        }

        Arrays.sort(arr);

        int N = quantity.length;

        int[] sec = new int[N];

        Arrays.sort(arr);

        if (Arrays.stream(quantity).max().orElse(0) > Arrays.stream(arr).max().orElse(0))
            return false;

        for (int i = 50, j = 0; i >= 0 && j < N; i--, j++)
            sec[j] = arr[i];

        return dfs(quantity, 0, arr);
    }

    public static boolean dfs(
            int[] quantity,
            int index,
            int[] arr
    ) {
        if (index >= quantity.length)
            return true;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] >= quantity[index]) {

                arr[i] -= quantity[index];

                if (dfs(quantity, index + 1, arr))
                    return true;

                arr[i] += quantity[index];
            }

        }

        return false;
    }

}
