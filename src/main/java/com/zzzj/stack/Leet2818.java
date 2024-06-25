package com.zzzj.stack;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2024-06-19 16:22
 */
public class Leet2818 {

    public static void main(String[] args) {

        System.out.println(maximumScore(LeetUtils.convertLists0("[1,1,2,18,1,9,3,1]"), 32));

        System.exit(0);

        System.out.println(maximumScore(LeetUtils.convertLists0("[8,3,9,3,8]"), 2));

        System.out.println(maximumScore(LeetUtils.convertLists0("[19,12,14,6,10,18]"), 3));

        System.out.println(maximumScore(LeetUtils.convertLists0("[1,1,2,18,1,9,3,1]"), 32));

    }

    private static final long MOD = (long) 1e9 + 7;
    private static final int MX = (int) 1e5 + 1;
    private static final int[] omega = new int[MX];

    static {
        for (int i = 2; i < MX; i++)
            if (omega[i] == 0) // i 是质数
                for (int j = i; j < MX; j += i)
                    omega[j]++; // i 是 j 的一个质因子
    }

    public static int maximumScore(List<Integer> nums, int k) {

        int N = nums.size();

        int[][] arr = new int[N][3];

        for (int i = 0; i < N; i++) {
            arr[i][0] = nums.get(i);
            arr[i][1] = omega[nums.get(i)];
            arr[i][2] = i;
        }

        // System.out.println("arr = " + Arrays.deepToString(arr));

        LinkedList<Integer> stack = new LinkedList<>();

        int[] left = new int[N];

        int[] right = new int[N];

        for (int i = 0; i < N; i++) {

            left[i] = i;

            Integer last = null;

            while (!stack.isEmpty() && arr[stack.peekLast()][1] < arr[i][1]) {

                last = stack.removeLast();

                right[last] = i;
            }

            if (last != null)
                left[i] = left[last];

            stack.add(i);
        }

        while (!stack.isEmpty())
            right[stack.removeLast()] = N;

        Arrays.sort(arr, (o1, o2) -> o2[0] - o1[0]);

        int index = 0;

        int mod = (int) (1e9 + 7);

        long ans = 1;

        while (k > 0 && index < N) {

            int[] item = arr[index];

            int it = item[2];

            long cnt = Math.min(
                    (long) (it - left[it] + 1) * (right[it] - it),
                    k
            );

            ans = ans * pow(item[0], (int) cnt) % mod;

            k -= cnt;

            index++;
        }

        return (int) ans;
    }

    private static long pow(long x, int n) {
        long res = 1L;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) res = res * x % MOD;
            x = x * x % MOD;
        }
        return res;
    }
}
