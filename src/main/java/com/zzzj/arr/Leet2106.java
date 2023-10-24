package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2023-10-23 15:35
 */
public class Leet2106 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {

            int M = 30;

            int limit = 30;

            int N = 50;

            int[][] fruits = new int[M][2];

            for (int j = 0; j < M; j++) {
                fruits[j][0] = LeetUtils.random.nextInt(N);
                fruits[j][1] = LeetUtils.random.nextInt(limit) + 1;
            }

            Arrays.sort(fruits, Comparator.comparingInt(o -> o[0]));

            int start = LeetUtils.random.nextInt(N);

            int k = LeetUtils.random.nextInt(N);

            int r = maxTotalFruits(fruits, start, k);

            int rr = right(fruits, start, k);

            if (r != rr) {
                System.out.println("fruits = " + Arrays.deepToString(fruits));
                System.out.println("start = " + start);
                System.out.println("k = " + k);
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                return;
            }
        }

        System.out.println("Ok");
    }

    public static int maxTotalFruits(int[][] fruits, int startPos, int k) {

        int N = fruits.length;

        int limit = startPos + k + 1;

        int[] preSum = new int[limit + 1];

        for (int[] fruit : fruits) {
            if (fruit[0] + 1 <= limit)
                preSum[fruit[0] + 1] = fruit[1];
        }

        for (int i = 1; i <= limit; i++)
            preSum[i] += preSum[i - 1];

        int ans = 0;

        for (int i = k; i >= 0; i--) {

            int left = Math.max(0, startPos - i);

            int right = Math.min(limit, startPos + Math.max(0, k - (i << 1)) + 1);

            ans = Math.max(ans, preSum[right] - preSum[left]);
        }

        for (int i = k; i >= 0; i--) {

            int right = Math.min(limit, startPos + i + 1);

            int left = Math.max(0, startPos - Math.max(0, k - (i << 1)));

            ans = Math.max(ans, preSum[right] - preSum[left]);
        }

        return ans;
    }

    public static int right(int[][] fruits, int startPos, int k) {
        int n = fruits[fruits.length - 1][0] + 1;
        int arr[] = new int[n + 1];
        for (int a[] : fruits) {
            arr[a[0] + 1] = a[1];
        }
        int ans = 0;
        for (int i = 1; i <= n; ++i) arr[i] += arr[i - 1];
        for (int i = Math.max(0, startPos - k); i <= Math.min(n - 1, startPos); ++i) {
            ans = Math.max(arr[Math.min(n - 1, Math.max(startPos, i + k - (startPos - i))) + 1] - arr[i], ans);
        }
        for (int i = Math.min(n - 1, startPos + k); i >= Math.max(0, startPos); --i) {
            ans = Math.max(-arr[Math.max(0, Math.min(startPos, i - k + (-startPos + i)))] + arr[i + 1], ans);
        }
        return ans;
    }

}
