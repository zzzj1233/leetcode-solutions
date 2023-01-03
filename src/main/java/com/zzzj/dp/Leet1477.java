package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-12-30 15:57
 */
public class Leet1477 {

    public static void main(String[] args) {

        System.out.println(minSumOfLengths(new int[]{7, 5, 6, 4, 7, 1, 1, 8, 6, 3}, 9));

//        System.exit(0);

        int N = 10000;

        for (int i = 0; i < N; i++) {

            int LEN = 1000;

            int[] arr = ArrayUtil.generateArray(LEN, 1, 1000);

            int target = LeetUtils.random.nextInt(100000);

            try {
                if (minSumOfLengths(arr, target) != right(arr, target)) {
                    System.out.println("Error");
                    System.out.println(Arrays.toString(arr));
                    System.out.println(target);
                    System.out.println(minSumOfLengths(arr, target));
                    System.out.println(right(arr, target));
                    return;
                }
            } catch (Exception e) {
                continue;
            }

        }

        System.out.println("Ok");
    }

    public static int minSumOfLengths(int[] arr, int target) {

        int N = arr.length;

        // 右边能凑出target的最短连续子数组
        int[] right = new int[N + 2];


        Arrays.fill(right, -1);

        int[] preSum = new int[N + 1];

        int ans = Integer.MAX_VALUE;

        Map<Integer, Integer> sumMap = new HashMap<>(N);

        sumMap.put(0, N);

        for (int i = N - 1; i >= 0; i--) {

            preSum[i] = preSum[i + 1] + arr[i];

            int min = Integer.MAX_VALUE;

            if (arr[i] == target) {
                right[i] = 1;
            } else if (sumMap.containsKey(preSum[i] - target)) {
                right[i] = right[i + 1] == -1 ? sumMap.get(preSum[i] - target) - i : Math.min(
                        sumMap.get(preSum[i] - target) - i, right[i + 1]
                );
            } else {
                right[i] = right[i + 1];
            }

            sumMap.put(preSum[i], i);
        }

        sumMap.clear();

        sumMap.put(0, 0);

        preSum[0] = 0;

        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + arr[i - 1];

            int len = -1;

            if (arr[i - 1] == target) {
                len = 1;
            } else if (sumMap.containsKey(preSum[i] - target)) {
                len = i - sumMap.get(preSum[i] - target);
            }

            sumMap.put(preSum[i], i);

            if (len != -1 && right[i] != -1) {
                ans = Math.min(ans, len + right[i]);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int right(int[] arr, int target) {
        int sum = 0, l = 0, r = -1;
        int len = arr.length;
        int[] start = new int[len];
        int[] end = new int[len];
        while (r < len && l < len) {
            while (r < len - 1 && sum < target) {
                sum += arr[++r];
            }
            if (sum == target) {
                int size = r - l + 1;
                start[l] = size;
                end[r] = size;
            }
            sum -= arr[l++];
        }
        int starts[] = new int[len];
        int ends[] = new int[len];
        int mine = Integer.MAX_VALUE;
        int mins = Integer.MAX_VALUE;
        for (int i = 0, j = len - 1; i < len; i++, j--) {
            if (mins != Integer.MAX_VALUE) {
                starts[j] = mins;
            }
            int e = end[i];
            int s = start[j];
            if (e != 0) {
                mine = Math.min(mine, e);
            }
            if (s != 0) {
                mins = Math.min(mins, s);
            }
            if (mine != Integer.MAX_VALUE) {
                ends[i] = mine;
            }
        }
        int ans = Integer.MAX_VALUE, cur = 0;
        for (int i = 0; i < len; i++) {
            if (ends[i] != 0 && starts[i] != 0 && (cur = ends[i] + starts[i]) < ans) {
                ans = cur;
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
