package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-06-20 18:40
 */
public class Leet650 {

    public static void main(String[] args) {
        for (int n = 1; n <= 1000; n++) {
            if (minSteps(n) != right(n)) {
                System.out.println(n);
                System.out.println(minSteps(n));
                System.out.println(right(n));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int minSteps(int n) {
        return dp(n);
    }

    public static int dp(int n) {
        int[] dp = new int[n + 1];

        // 此时屏幕上已经有一个A了
        // 我们能做的只有copy和paste

        // 2A = 2, copy + paste
        // 3A = 3, copy + paste + paste
        // 4A = 4, copy + paste + copy + paste              = 2A + copy + paste
        // 5A = 5, copy + paste + paste + paste + paste     = 3A + 2paste
        // 6A = 5, copy + paste + copy + paste + paste      = 4A + paste
        // 7A = 7,
        // 8A = 6
        // 9A = 6
        // 10A = 7
        // 11A = 11
        // 15A = 8,  在5后面加一次copy和两次paste

        // 1. 当前数如果是奇数
        // 2. 当前数如果是偶数
        // 2.1 当前数/2+2

        OUTER:
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i / 2] + 2;
            } else {
                for (int j = i / 3; j > 1; j--) {
                    if (i % j == 0) {
                        dp[i] = dp[j] + (i / j);
                        continue OUTER;
                    }
                }
                dp[i] = i;
            }
        }

        return dp[n];
    }

    public static int right(int n) {
        int res = 0;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                res += i;
                n /= i;
            }
        }
        return res;
    }

}
