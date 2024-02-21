package com.zzzj.acw;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author zzzj
 * @create 2024-01-31 11:47
 */
public class Q1087 {

    public static void main(String[] args) {

        r2("5 2\n" +
                "1\n" +
                "2\n" +
                "3\n" +
                "4\n" +
                "5");

        System.out.println(f(2, new int[]{2, 3, 1, 1}));

        System.exit(0);

        int N = 4;

        for (int i = 0; i < 10000; i++) {

            int K = Math.min(N - 1, LeetUtils.random.nextInt(N) + 1);

            int[] nums = ArrayUtil.generateArray(N, 1, N);

            long r = f(K, nums);

            long rr = right(K, nums);

            if (r != rr) {
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                System.out.println("K = " + K);
                System.out.println("nums = " + Arrays.toString(nums));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static long f(int K, int[] e) {

        int N = e.length;

        long[] ps = new long[N + 1];

        for (int i = 1; i <= N; i++)
            ps[i] = ps[i - 1] + e[i - 1];

        long[] f = new long[N + 1];

        long ans = 0;

        LinkedList<Integer> stack = new LinkedList<>();

        stack.add(0);

        for (int i = 1; i <= N; i++) {

            if (stack.peekFirst() < i - K)
                stack.removeFirst();

            while (!stack.isEmpty() && e[stack.peekLast()] >= e[i - 1])
                stack.removeLast();

            stack.add(i - 1);

            f[i] = f[Math.max(0, i - K - 1)] + ps[i] - ps[Math.max(0, i - K - 1)] - e[stack.peekFirst()];

            ans = Math.max(ans, f[i]);
        }

        return ans;
    }

    public static long g(
            long[] f,
            int x,
            int i,
            long[] ps
    ) {
        return f[x] + ps[i] - ps[x + 1];
    }

    public static long right(int K, int[] nums) {

        int N = nums.length;

        long[] f = new long[N + 1];

        long[] ps = new long[N + 1];

        for (int i = 1; i <= N; i++)
            ps[i] = ps[i - 1] + nums[i - 1];

        for (int i = 1; i <= K; i++)
            f[i] = ps[i];

        long ans = 0;

        for (int i = K + 1; i <= N; i++) {

            int leftLimit = i - K - 1;

            for (int j = leftLimit; j < i; j++) {
                f[i] = Math.max(
                        f[i],
                        f[j] + ps[i] - ps[j + 1]
                );
            }

            ans = Math.max(ans, f[i]);
        }

        return ans;
    }

    static final int N = 100010;
    static long[] f = new long[N];
    static long[] s = new long[N];
    static int[] q = new int[N];

    static long g(int i) {
        if (i == 0) return 0;
        return f[i - 1] - s[i];
    }

    public static void r2(String source) {
        Scanner scanner = new Scanner(source);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        for (int i = 1; i <= n; i++) {
            s[i] = scanner.nextLong();
            s[i] += s[i - 1];
        }

        int hh = 0, tt = 0;
        for (int i = 1; i <= n; i++) {
            if (q[hh] < i - k) hh++;
            f[i] = Math.max(f[i - 1], g(q[hh]) + s[i]);
            while (hh <= tt && g(q[tt]) <= g(i)) tt--;
            q[++tt] = i;
        }

        System.out.println(f[n]);
    }

}
