package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;
import com.zzzj.util.Unresolved;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-08-29 17:45
 */
@Unresolved
public class Leet1997 {

    public static void main(String[] args) {

        System.exit(0);

        for (int i = 0; i < 10; i++) {
            int[] arr = ArrayUtil.generateArray(10, 0, 1000);
            arr[0] = 0;
            for (int j = 1; j < arr.length; j++) {
                arr[j] = LeetUtils.random.nextInt(j);
            }
            if (firstDayBeenInAllRooms(arr) != right(arr)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(firstDayBeenInAllRooms(arr));
                System.out.println(right(arr));
                return;
            }
        }
        System.out.println("Ok");
    }

    // 0 <= nextVisit[i] <= i
    public static int firstDayBeenInAllRooms(int[] nextVisit) {
        int N = nextVisit.length;

        int[] dp = new int[N];

        // 从 0 ~ 1 需要2次
        dp[0] = 2;

        for (int i = 1; i < N - 1; i++) {
            int prev = nextVisit[i];
            dp[i] = dp[i - 1] - dp[prev] + 1;
        }

        return dp[N - 2];
    }

    public static int right(int[] nextVisit) {
        int n = nextVisit.length;
        int mod = 1000000007;
        long[] dp = new long[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = (2 * dp[i - 1] + mod - dp[nextVisit[i - 1]] + 2) % mod;
        }
        return (int) dp[n - 1];
    }

}
