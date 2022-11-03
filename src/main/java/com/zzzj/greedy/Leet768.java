package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2022-11-01 10:18
 */
public class Leet768 {

    public static void main(String[] args) {
        System.out.println(maxChunksToSorted(new int[]{5, 1, 1, 8, 1, 6, 5, 9, 7, 8}));
    }

    public static int maxChunksToSorted(int[] arr) {
        int N = arr.length;

        int[] min = new int[N];

        min[N - 1] = arr[N - 1];

        for (int i = N - 2; i >= 0; i--) {
            min[i] = Math.min(min[i + 1], arr[i]);
        }

        int ans = 0;

        for (int i = 0; i < N; ) {
            int j = i;
            int max = arr[j];
            for (; j < N; j++) {
                if (min[j] >= max) {
                    break;
                }
                max = Math.max(arr[j], max);
            }
            if (j == i) {
                j++;
            }
            ans++;
            i = j;
        }

        return ans;
    }

}
