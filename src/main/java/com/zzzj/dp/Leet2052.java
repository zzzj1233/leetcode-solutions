package com.zzzj.dp;

import cn.hutool.core.util.ArrayUtil;
import com.zzzj.leet.LeetUtils;


/**
 * @author zzzj
 * @create 2022-10-27 17:02
 */
public class Leet2052 {

    public static void main(String[] args) {
        OUTER:
        for (int i = 0; i < 10000; i++) {
            int N = LeetUtils.random.nextInt(1000);
            int M = LeetUtils.random.nextInt(1000) + 1;

            String[] arr = new String[N];

            for (int j = 0; j < N; j++) {
                arr[j] = LeetUtils.randomString(LeetUtils.random.nextInt(M) + 1, false);
            }

            String str = ArrayUtil.join(arr, " ");

            int k = LeetUtils.random.nextInt(10000);

            for (int j = 0; j < N; j++) {
                if (arr[j].length() > k) {
                    continue OUTER;
                }
            }

            if (minimumCost(str, k) != right(str, k)) {
                System.out.println("Error");
                System.out.println(str);
                System.out.println(k);
                System.out.println(minimumCost(str, k));
                System.out.println(right(str, k));
                return;
            }

        }

        System.out.println("OK~");


    }

    public static int minimumCost(String sentence, int k) {
        int length = sentence.length();

        if (length <= k) {
            return 0;
        }

        String[] words = sentence.split(" ");

        int N = words.length;

        long[] dp = new long[N];

        int[] list = new int[N];
        list[0] = words[0].length();

        for (int i = 1; i < N; i++) {
            list[i] = list[i - 1] + words[i].length() + 1;
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int len = words[i].length();

            long cost = (i - 1 >= 0 ? dp[i - 1] : 0) + ((long) (k - len)) * (k - len);

            for (int j = i - 1; j >= 0; j--) {
                len += words[j].length() + 1;
                if (len > k) {
                    break;
                }
                cost = Math.min(cost, (j - 1 >= 0 ? dp[j - 1] : 0) + ((long) (k - len)) * (k - len));
            }

            dp[i] = cost;

            if (length - list[i] <= k) {
                ans = (int) Math.min(ans, dp[i]);
            }
        }

        return ans;
    }

    public static int right(String sentence, int k) {
        String[] split = sentence.split(" ");
        int n = split.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int len = split[i - 1].length();
            dp[i] = dp[i - 1] + (i != n ? (k - len) * (k - len) : 0);
            int j = i - 1;
            while (j >= 1 && len + 1 + split[j - 1].length() <= k) {
                len += 1 + split[j - 1].length();
                dp[i] = Math.min(dp[i], dp[j - 1] + (i != n ? (k - len) * (k - len) : 0));
                j--;
            }
        }
        return dp[n];
    }

}
